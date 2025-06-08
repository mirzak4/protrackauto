import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleBodyType, VehicleCategory, VehicleDTO } from 'app/core/models/vehicle.model';
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
  vehicles: VehicleDTO[] = [];
  isDeleteConfirmOpen = false;
  vehicleToDelete: VehicleDTO | null = null;

  VehicleCategory = VehicleCategory;  
  VehicleBodyType = VehicleBodyType;

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

  addVehicle() {
    this.router.navigate(['/vehicles/new']);
  }

  editVehicle(id: number) {
    this.router.navigate(['/vehicles/edit', id]);
  }

  deleteVehicle(vehicle: VehicleDTO) {
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
}
