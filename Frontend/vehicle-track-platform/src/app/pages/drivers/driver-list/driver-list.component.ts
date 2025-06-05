import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DriverService } from 'app/core/services/driver.service';
import { DriverDTO, UserDTO } from 'app/core/models/driver.model';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-driver-list',
  standalone: true,
  templateUrl: './driver-list.component.html',
  imports: [CommonModule, MatSnackBarModule],
  styleUrls: ['./driver-list.component.css']
})
export class DriverListComponent implements OnInit {
  drivers: DriverDTO[] = [];
  isDeleteConfirmOpen = false;
  driverToDelete: DriverDTO | null = null;

  constructor(
    private driverService: DriverService,
    private router: Router,
    private snackBar: MatSnackBar
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
    if (!this.driverToDelete?.id) return;

    this.driverService.deleteDriver(this.driverToDelete.id).subscribe({
      next: () => {
        this.loadDrivers();
        this.isDeleteConfirmOpen = false;
        this.snackBar.open('Driver deleted successfully.', 'Dismiss', {
          duration: 3500,
          panelClass: ['snackbar-success']
        });
        this.driverToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting driver:', error);
        this.isDeleteConfirmOpen = false;
        this.driverToDelete = null;

        this.snackBar.open('Error deleting driver. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
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