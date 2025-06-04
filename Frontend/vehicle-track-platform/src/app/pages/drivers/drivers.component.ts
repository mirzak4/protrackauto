import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DriverService } from 'app/core/services/driver.service';
import { DriverDTO, UserDTO } from 'app/core/models/driver.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-drivers',
  templateUrl: './drivers.component.html',
  imports: [CommonModule],
  styleUrls: ['./drivers.component.css']
})
export class DriversComponent implements OnInit {
  drivers: DriverDTO[] = [];
  isDeleteConfirmOpen = false;
  driverToDelete: DriverDTO | null = null;

  constructor(
    private driverService: DriverService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadDrivers();
  }

  loadDrivers() {
    this.driverService.getAllDrivers().subscribe({
      next: (drivers) => {
        this.drivers = drivers;
      },
      error: (error) => {
        console.error('Error loading drivers:', error);
      }
    });
  }

  addDriver() {
    this.router.navigate(['/drivers/new']);
  }

  editDriver(driverId: number) {
    this.router.navigate(['/drivers/edit', driverId]);
  }

  deleteDriver(driver: DriverDTO) {
    this.driverToDelete = driver;
    this.isDeleteConfirmOpen = true;
  }
  
  confirmDelete() {
    if (!this.driverToDelete) return;
    
    this.driverService.deleteDriver(this.driverToDelete.userId).subscribe({
      next: () => {
        this.loadDrivers();
        this.isDeleteConfirmOpen = false;
        this.driverToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting driver:', error);
        this.isDeleteConfirmOpen = false;
        this.driverToDelete = null;
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.driverToDelete = null;
  }

  getFullName(user: UserDTO | undefined): string {
    return user ? `${user.firstName} ${user.lastName}` : '';
  }
}