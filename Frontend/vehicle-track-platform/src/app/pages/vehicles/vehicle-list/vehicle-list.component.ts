import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleBodyType, VehicleCategory, Vehicle } from 'app/core/models/vehicle.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-vehicle-list',
  standalone: true,
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css'],
  imports: [CommonModule, MatSnackBarModule]
})
export class VehicleListComponent implements OnInit {
  vehicles: Vehicle[] = [];
  isDeleteConfirmOpen = false;
  vehicleToDelete: Vehicle | null = null;

  VehicleCategory = VehicleCategory;  
  VehicleBodyType = VehicleBodyType;

  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private vehicleService: VehicleService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.loadVehicles();
  }

  loadVehicles() {
    this.vehicleService.getAllVehicles().subscribe({
      next: (data) => this.vehicles = data,
      error: (err) => console.error('Error loading vehicles:', err)
    });
  }

  get pagedVehicles(): Vehicle[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return this.vehicles.slice(start, start + this.itemsPerPage);
  }

  get totalPages(): number {
    return Math.ceil(this.vehicles.length / this.itemsPerPage);
  }

  changePage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }

  addVehicle() {
    this.router.navigate(['/vehicles/new']);
  }

  editVehicle(id: number) {
    this.router.navigate(['/vehicles/edit', id]);
  }

  deleteVehicle(vehicle: Vehicle) {
    this.vehicleToDelete = vehicle;
    this.isDeleteConfirmOpen = true;
  }

  confirmDelete() {
    if (!this.vehicleToDelete?.id) return;

    this.vehicleService.deleteVehicle(this.vehicleToDelete.id).subscribe({
      next: () => {
        this.loadVehicles();
        this.isDeleteConfirmOpen = false;
        this.snackBar.open('Vehicle deleted successfully.', 'Dismiss', {
          duration: 3500,
          panelClass: ['snackbar-success']
        });
        this.vehicleToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting vehicle:', error);
        this.isDeleteConfirmOpen = false;
        this.vehicleToDelete = null;
        this.snackBar.open('Error deleting vehicle. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.vehicleToDelete = null;
  }

  formatEnumLabel(value: string | null | undefined): string {
    if (!value) return '-';
    return value
      .toLowerCase()
      .replace(/_/g, ' ')
      .replace(/\b\w/g, c => c.toUpperCase());
  }
}
