import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DriverService } from 'app/core/services/driver.service';
import { DriverDTO } from 'app/core/models/driver.model';

@Component({
  selector: 'app-driver-form',
  templateUrl: './driver-form.component.html',
  imports: [FormsModule],
  styleUrls: ['./driver-form.component.css']
})
export class DriverFormComponent implements OnInit {
  isEditMode = false;
  driver: Partial<DriverDTO> = {
    licenseNumber: '',
    licenseExpiry: '',
    employmentDate: '',
    user: {
      id: 0,
      firstName: '',
      lastName: '',
      email: '',
      phoneNumber: ''
    }
  };

  constructor(
    private driverService: DriverService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadDriver(+id);
    }
  }

  loadDriver(id: number) {
    this.driverService.getDriverById(id).subscribe({
      next: (driver) => {
        this.driver = driver;
        if (driver.licenseExpiry) {
          this.driver.licenseExpiry = this.formatDateForInput(driver.licenseExpiry);
        }
        if (driver.employmentDate) {
          this.driver.employmentDate = this.formatDateForInput(driver.employmentDate);
        }
      },
      error: (error) => {
        console.error('Error loading driver:', error);
      }
    });
  }

  saveDriver() {
    if (!this.isFormValid()) return;

    const driverData: DriverDTO = {
      id: this.driver.id || 0,
      userId: this.driver.userId || 0,
      licenseNumber: this.driver.licenseNumber || '',
      licenseExpiry: new Date(this.driver.licenseExpiry || '').toISOString(),
      employmentDate: new Date(this.driver.employmentDate || '').toISOString(),
      user: {
        id: this.driver.user?.id || 0,
        firstName: this.driver.user?.firstName || '',
        lastName: this.driver.user?.lastName || '',
        email: this.driver.user?.email || '',
        phoneNumber: this.driver.user?.phoneNumber || ''
      }
    };

    const request = this.isEditMode
      ? this.driverService.updateDriver(driverData.id, driverData)
      : this.driverService.createDriver(driverData);

    request.subscribe({
      next: () => {
        this.router.navigate(['/drivers']);
      },
      error: (error) => {
        console.error('Error saving driver:', error);
      }
    });
  }

  isFormValid(): boolean {
    return !!(
      this.driver.licenseNumber &&
      this.driver.licenseExpiry &&
      this.driver.employmentDate &&
      this.driver.user?.firstName &&
      this.driver.user?.lastName &&
      this.driver.user?.email &&
      this.driver.user?.phoneNumber
    );
  }

  formatDateForInput(dateString: string): string {
    const date = new Date(dateString);
    return date.toISOString().split('T')[0];
  }
}