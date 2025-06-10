import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceRequestService } from 'app/core/services/service-request.service';
import { CompanyService } from 'app/core/services/company.service';
import { ServiceRequest } from 'app/core/models/service-request.model';
import { CompanyDTO } from 'app/core/models/company.model';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
    selector: 'app-service-request-list',
    standalone: true,
    templateUrl: './service-request-list.component.html',
    styleUrls: ['./service-request-list.component.css'],
    imports: [CommonModule, MatSnackBarModule]
})
export class ServiceRequestListComponent implements OnInit {
    serviceRequests: ServiceRequest[] = [];
    companies: CompanyDTO[] = [];
    companyMap: { [key: number]: string } = {};
    isDeleteConfirmOpen = false;
    serviceRequestToDelete: ServiceRequest | null = null;

    constructor(
        private serviceRequestService: ServiceRequestService,
        private companyService: CompanyService,
        private router: Router,
        private snackBar: MatSnackBar
    ) { }

    ngOnInit() {
        this.loadCompanies();
        this.loadServiceRequests();
    }

    loadCompanies() {
        this.companyService.getAll().subscribe({
            next: (companies) => {
                this.companies = companies;
                companies.forEach(c => this.companyMap[c.id] = c.name);
            },
            error: () => {
                console.error('Error loading companies');
            }
        });
    }

    loadServiceRequests() {
        // MOCK DATA ONLY – remove this when backend is ready
        // this.serviceRequests = [
        //     {
        //         id: 1,
        //         serviceType: 1, // MAINTENANCE
        //         fiscalReceiptNumber: 1001,
        //         cost: 150.00,
        //         status: 1, // REQUESTED
        //         requestDate: '2025-06-01',
        //         requestedBy: 'Alice Johnson',
        //         vehicleId: 12,
        //         servicerId: 101
        //     },
        //     {
        //         id: 2,
        //         serviceType: 2, // REPAIR
        //         fiscalReceiptNumber: 1002,
        //         cost: 320.00,
        //         status: 3, // IN_PROGRESS
        //         requestDate: '2025-06-05',
        //         requestedBy: 'Bob Smith',
        //         vehicleId: 15,
        //         servicerId: 102
        //     },
        //     {
        //         id: 3,
        //         serviceType: 2, // INSPECTION
        //         fiscalReceiptNumber: 1003,
        //         cost: 90.00,
        //         status: 4, // COMPLETED
        //         requestDate: '2025-06-07',
        //         requestedBy: 'Carol Nguyen',
        //         vehicleId: 8,
        //         servicerId: 103
        //     }
        // ];

        this.serviceRequestService.getAllServiceRequests().subscribe({
            next: (requests) => {
                this.serviceRequests = requests;
            },
            error: (error) => {
                console.error('Error loading service requests:', error);
            }
        });

    }

    addServiceRequest() {
        this.router.navigate(['/service-requests/new']);
    }

    editServiceRequest(id: number) {
        this.router.navigate(['/service-requests/edit', id]);
    }

    deleteServiceRequest(request: ServiceRequest) {
        this.serviceRequestToDelete = request;
        this.isDeleteConfirmOpen = true;
    }

    confirmDelete() {
        if (!this.serviceRequestToDelete?.id) return;

        this.serviceRequestService.deleteServiceRequest(this.serviceRequestToDelete.id).subscribe({
            next: () => {
                this.loadServiceRequests();
                this.isDeleteConfirmOpen = false;
                this.snackBar.open('Service request deleted successfully.', 'Dismiss', {
                    duration: 3500,
                    panelClass: ['snackbar-success']
                });
                this.serviceRequestToDelete = null;
            },
            error: (error) => {
                console.error('Error deleting service request:', error);
                this.isDeleteConfirmOpen = false;
                this.serviceRequestToDelete = null;

                this.snackBar.open('Error deleting service request. Please try again.', 'Close', {
                    duration: 3000,
                    panelClass: ['snackbar-error']
                });
            }
        });
    }

    cancelDelete() {
        this.isDeleteConfirmOpen = false;
        this.serviceRequestToDelete = null;
    }

    getServiceTypeName(type: number): string {
        switch (type) {
            case 1: return 'Regular Service Check';
            case 2: return 'Technical Inspection for Registration';
            default: return 'Unknown';
        }
    }


    getStatusName(status: number): string {
        switch (status) {
            case 1: return 'Requested';
            case 2: return 'Scheduled';
            case 3: return 'In Progress';
            case 4: return 'Awaiting Parts';
            case 5: return 'Completed';
            case 6: return 'Canceled';
            default: return 'Unknown';
        }
    }

}
