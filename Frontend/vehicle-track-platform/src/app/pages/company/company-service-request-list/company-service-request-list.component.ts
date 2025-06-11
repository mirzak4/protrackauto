import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceRequestStatus, ServiceType } from 'app/core/models/enums/service-request.enums';
import { ServiceRequest } from 'app/core/models/service-request.model';
import { CompanyService } from 'app/core/services/company.service';
import { ServiceRequestService } from 'app/core/services/service-request.service';
import { VehicleService } from 'app/core/services/vehicle.service';

@Component({
  selector: 'app-company-service-request-list',
  imports: [CommonModule],
  templateUrl: './company-service-request-list.component.html',
  styleUrl: './company-service-request-list.component.css'
})
export class CompanyServiceRequestListComponent implements OnInit {
  serviceRequests: ServiceRequest[] = [];
  servicerId!: number;
  vehiclePlates: { [key: number]: string } = {};
  companyName: string = '';

  currentPage = 1;
  itemsPerPage = 10;

  serviceTypeLabels: { [key: number]: string } = {
    1: 'Regular Service',
    2: 'Technical Inspection'
  };

  statusLabels: { [key: number]: string } = {
    1: 'Requested',
    2: 'Scheduled',
    3: 'In Progress',
    4: 'Awaiting Parts',
    5: 'Completed',
    6: 'Canceled'
  };


  constructor(
    private route: ActivatedRoute,
    private serviceRequestService: ServiceRequestService,
    private vehicleService: VehicleService,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.servicerId = +idParam;

        this.companyService.getById(this.servicerId).subscribe({
          next: (company) => {
            this.companyName = company.name;
          },
          error: (err) => {
            console.error(`Error fetching company with ID ${this.servicerId}:`, err);
          }
        });

        this.loadServiceRequests();
      }
    });
  }

  loadServiceRequests(): void {
    this.serviceRequestService.getServiceRequestsByServicerId(this.servicerId).subscribe({
      next: (data) => {
        this.serviceRequests = data;

        data.forEach(request => {
          if (request.vehicleId && !this.vehiclePlates[request.vehicleId]) {
            this.vehicleService.getVehicleById(request.vehicleId).subscribe({
              next: (vehicle) => {
                this.vehiclePlates[vehicle.id] = vehicle.licensePlate;
              },
              error: (err) => {
                console.error(`Error fetching vehicle with ID ${request.vehicleId}:`, err);
              }
            });
          }
        });
      },
      error: (err) => console.error('Error fetching service requests:', err),
    });
  }

  getServiceTypeName(type: number): string {
    return this.serviceTypeLabels[type] ?? 'Unknown';
  }

  getStatusName(status: number): string {
    return this.statusLabels[status] ?? 'Unknown';
  }

  get pagedServiceRequests(): ServiceRequest[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return this.serviceRequests.slice(start, start + this.itemsPerPage);
  }

  get totalPages(): number {
    return Math.ceil(this.serviceRequests.length / this.itemsPerPage);
  }

  changePage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
}
