import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentStatus, ViolationType } from 'app/core/models/enums/traffic-fine.enum';
import { TrafficFine } from 'app/core/models/traffic-fine.model';
import { TrafficFineService } from 'app/core/services/traffic-fine.service';

@Component({
  selector: 'app-vehicle-fines',
  imports: [CommonModule],
  templateUrl: './vehicle-fines.component.html',
  styleUrls: ['./vehicle-fines.component.css']
})
export class VehicleFinesComponent implements OnInit {
  vehicleId!: number;
  fines: TrafficFine[] = [];
  fineToDelete: TrafficFine | null = null;
  isDeleteConfirmOpen = false;

  ViolationType = ViolationType;
  PaymentStatus = PaymentStatus;

  currentPage = 1;
  pageSize = 10;

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

  constructor(
    private route: ActivatedRoute,
    private fineService: TrafficFineService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.vehicleId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadFines();
  }

  loadFines() {
    this.fineService.getAll().subscribe({
      next: (fines) => {
        this.fines = fines.filter(f => f.vehicleId === this.vehicleId);
      },
      error: (err) => console.error('Error loading fines', err)
    });
  }

  formatDate(dateStr: string): string {
    return new Date(dateStr).toLocaleDateString();
  }

  formatEnumLabel(value: string | undefined): string {
    if (!value) return '-';
    return value
      .toLowerCase()
      .replace(/_/g, ' ')
      .replace(/\b\w/g, c => c.toUpperCase());
  }

  getStatusClass(status: PaymentStatus) {
    switch (status) {
      case PaymentStatus.PAID: return 'status-paid';
      case PaymentStatus.PENDING: return 'status-pending';
      default: return '';
    }
  }


  get pagedFines(): TrafficFine[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.fines.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.fines.length / this.pageSize);
  }

  changePage(page: number) {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
  }

  deleteFine(fine: TrafficFine) {
    this.fineToDelete = fine;
    this.isDeleteConfirmOpen = true;
  }

  cancelDelete() {
    this.fineToDelete = null;
    this.isDeleteConfirmOpen = false;
  }

  confirmDelete() {
    if (!this.fineToDelete) return;

    this.fineService.delete(this.fineToDelete.id!).subscribe({
      next: () => {
        this.fines = this.fines.filter(f => f.id !== this.fineToDelete!.id);
        this.cancelDelete();
      },
      error: (err) => {
        console.error('Error deleting fine', err);
        this.cancelDelete();
      }
    });
  }

  getFormattedIssueDate(): string {
    if (!this.fineToDelete?.issueDate) return '';
    return this.formatDate(this.fineToDelete.issueDate);
  }

  editFine(fineId: number) {
    this.router.navigate(['/traffic-fines/edit', fineId]);
  }

  addNewFine() {
    this.router.navigate(['/traffic-fines/new']);
  }

}
