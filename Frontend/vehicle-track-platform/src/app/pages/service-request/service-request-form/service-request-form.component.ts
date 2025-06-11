import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceRequest } from 'app/core/models/service-request.model';
import { VehicleService } from 'app/core/services/vehicle.service';
import { CompanyService } from 'app/core/services/company.service';
import { ServiceRequestService } from 'app/core/services/service-request.service';
import { ServiceType, ServiceRequestStatus } from 'app/core/models/enums/service-request.enums';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-service-request-form',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './service-request-form.component.html',
    styleUrls: ['./service-request-form.component.css']
})
export class ServiceRequestFormComponent implements OnInit {
    isEditMode = false;
    hasChanges = false;
    isLoading = true;

    serviceRequest: ServiceRequest = {
        serviceType: 1,
        fiscalReceiptNumber: 0,
        cost: 0,
        status: 1,
        requestDate: '',
        requestedBy: '',
        vehicleId: 0,
        servicerId: 0,
    };

    vehicles: any[] = [];
    servicers: any[] = [];
    serviceTypes = [1, 2];
    statuses = [1, 2, 3, 4, 5, 6];

    constructor(
        private route: ActivatedRoute,
        private snackBar: MatSnackBar,
        private serviceRequestService: ServiceRequestService,
        private vehicleService: VehicleService,
        private companyService: CompanyService,
        private cdr: ChangeDetectorRef,
        public router: Router,
    ) { }

    ngOnInit(): void {
        this.loadInitialData();
    }

    onInputChange() {
        this.hasChanges = true;
    }

    loadInitialData() {
        const id = this.route.snapshot.paramMap.get('id');
        this.isEditMode = !!(id && id !== 'new');

        Promise.all([
            this.vehicleService.getAllVehicles().toPromise(),
            this.companyService.getAll('2').toPromise(),
        ]).then(([vehicles, servicers]) => {
            this.vehicles = vehicles ?? [];
            this.servicers = servicers ?? [];

            if (this.isEditMode && id) {
                this.serviceRequestService.getServiceRequestById(+id).subscribe(req => {
                    this.serviceRequest = {
                        ...req,
                        requestDate: this.formatDateForInput(req.requestDate)
                    };
                    this.cdr.detectChanges();
                });
            } else {
                this.serviceRequest.requestDate = this.formatDateForInput(new Date().toISOString());
                this.cdr.detectChanges();
            }

            this.isLoading = false;
        }).catch(err => {
            console.error('Error loading data:', err);
            this.isLoading = false;
        });
    }

    saveServiceRequest() {
        if (!this.isFormValid()) return;

        const reqToSave = {
            ...this.serviceRequest,
            requestDate: new Date(this.serviceRequest.requestDate).toISOString()
        };

        const request = this.isEditMode
            ? this.serviceRequestService.updateServiceRequest(this.serviceRequest.id!, reqToSave)
            : this.serviceRequestService.createServiceRequest(reqToSave);

        request.subscribe({
            next: () => {
                this.snackBar.open(
                    this.isEditMode ? 'Service request updated!' : 'Service request created!',
                    'OK', { duration: 3000 }
                );
                this.hasChanges = false;
                this.router.navigate(['/service-requests']);
            },
            error: (err) => {
                console.error('Save error:', err);
                this.snackBar.open(`Error: ${err.message || 'Failed to save'}`, 'Close', { duration: 4000 });
            }
        });
    }

    isFormValid(): boolean {
        const r = this.serviceRequest;
        return !!(
            r.serviceType && r.fiscalReceiptNumber && r.cost &&
            r.status && r.requestDate && r.requestedBy &&
            r.vehicleId && r.servicerId
        );
    }

    formatDateForInput(dateStr: string): string {
        try {
            return new Date(dateStr).toISOString().split('T')[0];
        } catch {
            return '';
        }
    }

    getStatusLabel(value: number): string {
        switch (value) {
            case 1: return 'Requested';
            case 2: return 'Scheduled';
            case 3: return 'In Progress';
            case 4: return 'Awaiting Parts';
            case 5: return 'Completed';
            case 6: return 'Canceled';
            default: return 'Unknown';
        }
    }

    getServiceTypeLabel(value: number): string {
        switch (value) {
            case 1: return 'Regular Service Check';
            case 2: return 'Technical Inspection for Registration';
            default: return 'Unknown';
        }
    }

}
