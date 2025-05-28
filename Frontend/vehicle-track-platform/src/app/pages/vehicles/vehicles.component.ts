import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.css'
})
export class VehiclesComponent {
  vehicles = [
    { id: 1, plate: 'ABC-123', model: 'Ford Transit', driver: 'John Doe', status: 'active' },
    { id: 2, plate: 'XYZ-789', model: 'Mercedes Sprinter', driver: 'Jane Smith', status: 'inactive' }
  ];

  isModalOpen = false;
  isEditMode = false;

  isDeleteConfirmOpen = false;
  vehicleToDelete: any = null;

  currentVehicle: any = { id: null, plate: '', model: '', driver: '', status: 'active' };

  openAddModal() {
    this.isEditMode = false;
    this.currentVehicle = { id: null, plate: '', model: '', driver: '', status: 'active' };
    this.isModalOpen = true;
  }

  openEditModal(vehicle: any) {
    this.isEditMode = true;
    this.currentVehicle = { ...vehicle };
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  saveVehicle() {
    if (this.isEditMode) {
      const index = this.vehicles.findIndex(v => v.id === this.currentVehicle.id);
      if (index > -1) {
        this.vehicles[index] = { ...this.currentVehicle };
      }
    } else {
      const newId = Math.max(...this.vehicles.map(v => v.id), 0) + 1;
      this.vehicles.push({ ...this.currentVehicle, id: newId });
    }
    this.closeModal();
  }

  deleteVehicle(id: number) {
    const confirmed = confirm('Are you sure?');
    if (confirmed) {
      this.vehicles = this.vehicles.filter(v => v.id !== id);
    }
  }

  openDeleteConfirm(vehicle: any) {
    this.vehicleToDelete = vehicle;
    this.isDeleteConfirmOpen = true;
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.vehicleToDelete = null;
  }
  
  confirmDelete() {
    if (!this.vehicleToDelete) return;
  
    this.vehicles = this.vehicles.filter(v => v.id !== this.vehicleToDelete.id);
    this.isDeleteConfirmOpen = false;
    this.vehicleToDelete = null;
  }
  

}
