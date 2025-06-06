import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DriverService } from 'app/core/services/driver.service';
import { DriverDTO, UserDTO } from 'app/core/models/driver.model';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


@Component({
  selector: 'app-driver-form',
  standalone: true,
  templateUrl: './driver-form.component.html',
  imports: [FormsModule, MatSnackBarModule],
  styleUrls: ['./driver-form.component.css']
})
export class DriverFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;
  driver: DriverDTO = {
    id: 0,
    licenseNumber: '',
    licenseExpiry: '',
    employmentDate: '',
    user: {
      id: 0,
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      phoneNumber: '',
      birthDate: ''
    }
  };

  constructor(
    private driverService: DriverService,
    private route: ActivatedRoute,
    public router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadDriver(+id);
    }
  }

  onInputChange() {
    this.hasChanges = true;
  }


  loadDriver(id: number) {
    this.driverService.getDriverById(id).subscribe({
      next: (driver) => {
        this.driver = driver;
        this.driver.licenseExpiry = this.formatDateForInput(driver.licenseExpiry);
        this.driver.employmentDate = this.formatDateForInput(driver.employmentDate);
        if (driver.user.birthDate) {
          this.driver.user.birthDate = this.formatDateForInput(driver.user.birthDate);
        }
      },
      error: (error) => {
        console.error('Error loading driver:', error);
      }
    });
  }

  saveDriver() {
    if (!this.isFormValid()) return;

    const driverToSave: DriverDTO = {
      ...this.driver,
      licenseExpiry: new Date(this.driver.licenseExpiry).toISOString(),
      employmentDate: new Date(this.driver.employmentDate).toISOString(),
      user: {
        ...this.driver.user,
        birthDate: this.driver.user.birthDate
          ? new Date(this.driver.user.birthDate).toISOString()
          : ''
      }
    };

    const request = this.isEditMode
      ? this.driverService.updateDriver(driverToSave.id!, driverToSave)
      : this.driverService.createDriver(driverToSave);

    request.subscribe({
      next: (response) => {
        
        this.snackBar.open(
          this.isEditMode
            ? 'Driver information updated successfully.'
            : 'Driver created successfully.',
          'Dismiss',
          {
            duration: 3500,
            panelClass: ['snackbar-success']
          }
        );
        this.hasChanges = false;
        this.router.navigate(['/drivers']);
      },
      error: (error) => {
        console.error('Error saving driver:', error);

        this.snackBar.open('Error saving driver. Please try again.', 'Close', { duration: 3000 });
      }
    });
  }

  isFormValid(): boolean {
    return !!(
      this.driver.licenseNumber &&
      this.driver.licenseExpiry &&
      this.driver.employmentDate &&
      this.driver.user.firstName &&
      this.driver.user.lastName &&
      this.driver.user.email &&
      this.driver.user.username && 
      this.driver.user.phoneNumber
    );
  }

  formatDateForInput(dateString: string): string {
    return dateString ? new Date(dateString).toISOString().split('T')[0] : '';
  }
}
