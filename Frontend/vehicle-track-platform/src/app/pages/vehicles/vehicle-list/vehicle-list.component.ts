import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleDTO } from 'app/core/models/vehicle.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  imports: [CommonModule],
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {
  vehicles: VehicleDTO[] = [];
  isDeleteConfirmOpen = false;
  vehicleToDelete: VehicleDTO | null = null;

  constructor(
    private vehicleService: VehicleService,
    public router: Router
  ) {}

  ngOnInit() {
    this.loadVehicles();
  }

  loadVehicles() {
    this.vehicleService.getAllVehicles().subscribe({
      next: (vehicles) => {
        this.vehicles = vehicles;
      },
      error: (error) => {
        console.error('Error loading vehicles:', error);
      }
    });
  }

  addVehicle() {
    this.router.navigate(['/vehicles/new']);
  }

  editVehicle(vehicleId: number) {
    this.router.navigate(['/vehicles/edit', vehicleId]);
  }

  viewVehicle(vehicleId: number) {
    this.router.navigate(['/vehicles/details', vehicleId]);
  }

  deleteVehicle(vehicle: VehicleDTO) {
    this.vehicleToDelete = vehicle;
    this.isDeleteConfirmOpen = true;
  }
  
  confirmDelete() {
    if (!this.vehicleToDelete) return;
    
    this.vehicleService.deleteVehicle(this.vehicleToDelete.id).subscribe({
      next: () => {
        this.loadVehicles();
        this.isDeleteConfirmOpen = false;
        this.vehicleToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting vehicle:', error);
        this.isDeleteConfirmOpen = false;
        this.vehicleToDelete = null;
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.vehicleToDelete = null;
  }
}