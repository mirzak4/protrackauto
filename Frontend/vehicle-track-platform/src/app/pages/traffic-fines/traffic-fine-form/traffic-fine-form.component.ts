import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TrafficFine } from 'app/core/models/traffic-fine.model';
import { VehicleService } from 'app/core/services/vehicle.service';
import { DriverService } from 'app/core/services/driver.service';
import { TrafficFineService } from 'app/core/services/traffic-fine.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

const VIOLATION_TYPES = [
  { id: 1, label: 'Speeding' },
  { id: 2, label: 'Running red light' },
  { id: 3, label: 'Illegal parking' },
  { id: 4, label: 'No seatbelt' },
  { id: 5, label: 'Invalid documents' },
  { id: 6, label: 'Other' }
];

const PAYMENT_STATUSES = [
  { id: 1, label: 'Unpaid' },
  { id: 2, label: 'Paid' },
  { id: 3, label: 'Partial' },
  { id: 4, label: 'Cancelled' },
  { id: 5, label: 'Pending' }
];

@Component({
  selector: 'app-traffic-fine-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './traffic-fine-form.component.html',
  styleUrls: ['./traffic-fine-form.component.css']
})
export class TrafficFineFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;
  isLoading = true;

  trafficFine: TrafficFine = {
    violationType: 1,    
    paymentStatus: 1,
    issueDate: '',
    paymentDueDate: '',
    violationDescription: '',
    location: '',
    amount: 0,
    vehicleId: 0,
    driverId: 0
  };

  vehicles: any[] = [];
  drivers: any[] = [];

  violationTypes = VIOLATION_TYPES;
  paymentStatuses = PAYMENT_STATUSES;

  constructor(
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private trafficFineService: TrafficFineService,
    private vehicleService: VehicleService,
    private driverService: DriverService,
    private cdr: ChangeDetectorRef,
    public router: Router,
  ) {}

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
      this.driverService.getAllDrivers().toPromise(),
    ]).then(([vehicles, drivers]) => {
      this.vehicles = vehicles ?? [];
      this.drivers = drivers ?? [];

      if (this.isEditMode && id) {
        this.trafficFineService.getById(+id).subscribe(fine => {
          this.trafficFine = {
            ...fine,
            issueDate: this.formatDateForInput(fine.issueDate),
            paymentDueDate: this.formatDateForInput(fine.paymentDueDate),
          };
          this.cdr.detectChanges();
        });
      } else {
        const today = new Date().toISOString();
        this.trafficFine.issueDate = this.formatDateForInput(today);
        this.trafficFine.paymentDueDate = this.formatDateForInput(today);
        this.cdr.detectChanges();
      }

      this.isLoading = false;
    }).catch(err => {
      console.error('Error loading data:', err);
      this.isLoading = false;
    });
  }

  saveFine() {
    if (!this.isFormValid()) return;

    const fineToSave = {
      ...this.trafficFine,
      issueDate: new Date(this.trafficFine.issueDate).toISOString(),
      paymentDueDate: new Date(this.trafficFine.paymentDueDate).toISOString(),
    };

    const request = this.isEditMode
      ? this.trafficFineService.update(fineToSave)
      : this.trafficFineService.create(fineToSave);

    request.subscribe({
      next: () => {
        this.snackBar.open(
          this.isEditMode ? 'Traffic fine updated!' : 'Traffic fine created!',
          'OK', { duration: 3000 }
        );
        this.hasChanges = false;
        this.router.navigate(['/traffic-fines']);
      },
      error: (err) => {
        console.error('Save error:', err);
        this.snackBar.open(`Error: ${err.message || 'Failed to save'}`, 'Close', { duration: 4000 });
      }
    });
  }

  isFormValid(): boolean {
    const f = this.trafficFine;
    return !!(
      f.violationType && f.amount > 0 && f.paymentStatus &&
      f.issueDate && f.paymentDueDate && f.vehicleId && f.driverId &&
      f.violationDescription.trim().length > 0 && f.location.trim().length > 0
    );
  }

  formatDateForInput(dateStr: string): string {
    try {
      return new Date(dateStr).toISOString().split('T')[0];
    } catch {
      return '';
    }
  }

  getViolationTypeLabel(value: number): string {
    return this.violationTypes.find(v => v.id === value)?.label ?? 'Unknown';
  }

  getPaymentStatusLabel(value: number): string {
    return this.paymentStatuses.find(p => p.id === value)?.label ?? 'Unknown';
  }
}
