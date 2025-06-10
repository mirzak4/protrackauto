import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { TrafficFineService } from 'app/core/services/traffic-fine.service';
import { CompanyService } from 'app/core/services/company.service';
import { TrafficFine } from 'app/core/models/traffic-fine.model';
import { CompanyDTO } from 'app/core/models/company.model';

@Component({
  selector: 'app-traffic-fine-list',
  standalone: true,
  templateUrl: './traffic-fine-list.component.html',
  styleUrls: ['./traffic-fine-list.component.css'],
  imports: [CommonModule, MatSnackBarModule]
})
export class TrafficFineListComponent implements OnInit {
  fines: TrafficFine[] = [];
  companies: CompanyDTO[] = [];
  companyMap: { [key: number]: string } = {};
  isDeleteConfirmOpen = false;
  fineToDelete: TrafficFine | null = null;

  constructor(
    private fineService: TrafficFineService,
    private companyService: CompanyService,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.loadCompanies();
    this.loadFines();
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

  loadFines() {
    // MOCK DATA ONLY – replace with backend call when ready
    // this.fines = [
    //   {
    //     id: 1,
    //     issueDate: '2025-06-01',
    //     paymentDueDate: '2025-06-15',
    //     violationDescription: 'Exceeded speed limit by 30km/h',
    //     violationType: 1, // SPEEDING
    //     location: 'Main Street, Springfield',
    //     paymentStatus: 1, // UNPAID
    //     amount: 150.00,
    //     vehicleId: 101,
    //     driverId: 201
    //   },
    //   {
    //     id: 2,
    //     issueDate: '2025-06-03',
    //     paymentDueDate: '2025-06-20',
    //     violationDescription: 'Parked in a no-parking zone',
    //     violationType: 2, // PARKING
    //     location: '5th Avenue, Shelbyville',
    //     paymentStatus: 2, // PAID
    //     amount: 100.00,
    //     vehicleId: 102,
    //     driverId: 202
    //   },
    //   {
    //     id: 3,
    //     issueDate: '2025-06-05',
    //     paymentDueDate: '2025-06-25',
    //     violationDescription: 'Driving without a valid registration',
    //     violationType: 3, // REGISTRATION
    //     location: 'Central Blvd, Capital City',
    //     paymentStatus: 1, // UNPAID
    //     amount: 300.00,
    //     vehicleId: 103,
    //     driverId: 203
    //   }
    // ];


    this.fineService.getAll().subscribe({
        next: (fines) => {
            this.fines = fines;
        },
        error: (error) => {
            console.error('Error loading traffic fines:', error);
        }
    });
  }

  addFine() {
    this.router.navigate(['/traffic-fines/new']);
  }

  editFine(id: number) {
    this.router.navigate(['/traffic-fines/edit', id]);
  }

  deleteFine(fine: TrafficFine) {
    this.fineToDelete = fine;
    this.isDeleteConfirmOpen = true;
  }

  confirmDelete() {
    if (!this.fineToDelete?.id) return;

    this.fineService.delete(this.fineToDelete.id).subscribe({
      next: () => {
        this.loadFines();
        this.isDeleteConfirmOpen = false;
        this.snackBar.open('Traffic fine deleted successfully.', 'Dismiss', {
          duration: 3500,
          panelClass: ['snackbar-success']
        });
        this.fineToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting traffic fine:', error);
        this.isDeleteConfirmOpen = false;
        this.fineToDelete = null;

        this.snackBar.open('Error deleting traffic fine. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.fineToDelete = null;
  }

  violationTypes = [
  { id: 1, label: 'Speeding' },
  { id: 2, label: 'Running red light' },
  { id: 3, label: 'Illegal parking' },
  { id: 4, label: 'No seatbelt' },
  { id: 5, label: 'Invalid documents' },
  { id: 6, label: 'Other' }
];

paymentStatuses = [
  { id: 1, label: 'Unpaid' },
  { id: 2, label: 'Paid' },
  { id: 3, label: 'Partial' },
  { id: 4, label: 'Cancelled' },
  { id: 5, label: 'Pending' }
];

getViolationTypeLabel(value: number): string {
  return this.violationTypes.find(v => v.id === value)?.label ?? 'Unknown';
}

getPaymentStatusLabel(value: number): string {
  return this.paymentStatuses.find(p => p.id === value)?.label ?? 'Unknown';
}

}

