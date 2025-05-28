import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-drivers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './drivers.component.html',
  styleUrl: './drivers.component.css'
})
export class DriversComponent {
  drivers = [
    { id: 1, name: 'John Doe', licenseNumber: 'XYZ12345', phone: '555-1234', status: 'active' },
    { id: 2, name: 'Jane Smith', licenseNumber: 'ABC67890', phone: '555-5678', status: 'inactive' }
  ];

  isModalOpen = false;
  isEditMode = false;
  isDeleteConfirmOpen = false;
  driverToDelete: any = null;

  currentDriver: any = { id: null, name: '', licenseNumber: '', phone: '', status: 'active' };

  openAddModal() {
    this.isEditMode = false;
    this.currentDriver = { id: null, name: '', licenseNumber: '', phone: '', status: 'active' };
    this.isModalOpen = true;
  }

  openEditModal(driver: any) {
    this.isEditMode = true;
    this.currentDriver = { ...driver };
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  saveDriver() {
    if (this.isEditMode) {
      const index = this.drivers.findIndex(d => d.id === this.currentDriver.id);
      if (index > -1) {
        this.drivers[index] = { ...this.currentDriver };
      }
    } else {
      const newId = Math.max(...this.drivers.map(d => d.id), 0) + 1;
      this.drivers.push({ ...this.currentDriver, id: newId });
    }
    this.closeModal();
  }

  deleteDriver(id: number) {
    const driver = this.drivers.find(d => d.id === id);
    this.driverToDelete = driver;
    this.isDeleteConfirmOpen = true;
  }
  
  confirmDelete() {
    if (!this.driverToDelete) return;
  
    this.drivers = this.drivers.filter(d => d.id !== this.driverToDelete.id);
    this.isDeleteConfirmOpen = false;
    this.driverToDelete = null;
  }
  

cancelDelete() {
  this.isDeleteConfirmOpen = false;
  this.driverToDelete = null;
}

}
