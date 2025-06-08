import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleDTO, VehicleCategory, VehicleBodyType } from 'app/core/models/vehicle.model';

@Component({
  selector: 'app-vehicle-form',
  standalone: true,
  templateUrl: './vehicle-form.component.html',
  imports: [CommonModule, FormsModule, MatSnackBarModule],
  styleUrls: ['./vehicle-form.component.css']
})
export class VehicleFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;
  isLoading = false;

  vehicle: VehicleDTO = {
    id: 0,
    licensePlate: '',
    vehicleBrandType: '',
    productionYear: new Date().getFullYear(),
    vehicleCategory: null,
    vehicleBodyType: null,
    fuelId: 0
  };

  vehicleCategories = [
    { value: VehicleCategory.TravelCar, label: 'Travel Car' },
    { value: VehicleCategory.Truck, label: 'Truck' },
    { value: VehicleCategory.Motorcycle, label: 'Motorcycle' },
    { value: VehicleCategory.Bus, label: 'Bus' },
    { value: VehicleCategory.Van, label: 'Van' },
    { value: VehicleCategory.SUV, label: 'SUV' },
    { value: VehicleCategory.Pickup, label: 'Pickup' },
    { value: VehicleCategory.Tractor, label: 'Tractor' },
    { value: VehicleCategory.Scooter, label: 'Scooter' },
    { value: VehicleCategory.Bicycle, label: 'Bicycle' }
  ];

  vehicleBodyTypes = [
    { value: VehicleBodyType.Sedan, label: 'Sedan' },
    { value: VehicleBodyType.Hatchback, label: 'Hatchback' },
    { value: VehicleBodyType.SUV, label: 'SUV' },
    { value: VehicleBodyType.Coupe, label: 'Coupe' },
    { value: VehicleBodyType.Convertible, label: 'Convertible' },
    { value: VehicleBodyType.Wagon, label: 'Wagon' },
    { value: VehicleBodyType.Pickup, label: 'Pickup' },
    { value: VehicleBodyType.Van, label: 'Van' },
    { value: VehicleBodyType.Minivan, label: 'Minivan' },
    { value: VehicleBodyType.Roadster, label: 'Roadster' },
    { value: VehicleBodyType.Jeep, label: 'Jeep' },
    { value: VehicleBodyType.Limousine, label: 'Limousine' },
    { value: VehicleBodyType.Truck, label: 'Truck' },
    { value: VehicleBodyType.Bus, label: 'Bus' },
    { value: VehicleBodyType.Motorcycle, label: 'Motorcycle' }
  ];

  fuelTypes = [
    { id: 1, name: 'Petrol' },
    { id: 2, name: 'Diesel' },
    { id: 3, name: 'Electric' },
    { id: 4, name: 'Hybrid' },
    { id: 5, name: 'LPG' },
    { id: 6, name: 'CNG' }
  ];

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    public router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadVehicle(+id);
    }
  }

  onInputChange() {
    this.hasChanges = true;
  }

  loadVehicle(id: number) {
    this.isLoading = true;
    this.vehicleService.getVehicleById(id).subscribe({
      next: (vehicle) => {
        this.vehicle = vehicle;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading vehicle:', error);
        this.isLoading = false;
        this.snackBar.open('Error loading vehicle data', 'Close', { duration: 3000 });
        this.router.navigate(['/vehicles']);
      }
    });
  }

  saveVehicle() {
    if (!this.isFormValid()) return;
    
    if (!this.isEditMode && !this.vehicle.vehicleCategory) {
      this.snackBar.open('Please select vehicle category', 'Close', { duration: 3000 });
      return;
    }

    const request = this.isEditMode
      ? this.vehicleService.updateVehicle(this.vehicle.id!, this.vehicle)
      : this.vehicleService.createVehicle(this.vehicle);

    request.subscribe({
      next: () => {
        this.snackBar.open(
          this.isEditMode ? 'Vehicle updated successfully.' : 'Vehicle created successfully.',
          'Dismiss',
          { duration: 3500, panelClass: ['snackbar-success'] }
        );
        this.hasChanges = false;
        this.router.navigate(['/vehicles']);
      },
      error: (error) => {
        console.error('Error saving vehicle:', error);
        this.snackBar.open('Error saving vehicle. Please try again.', 'Close', { duration: 3000 });
      }
    });
  }

  isFormValid(): boolean {
    return !!(
      this.vehicle.licensePlate &&
      this.vehicle.vehicleBrandType &&
      this.vehicle.productionYear &&
      (this.isEditMode || this.vehicle.vehicleCategory)
    );
  }

  getVehicleCategoryName(category: VehicleCategory | null): string {
    if (!category) return 'Unknown';
    
    const found = this.vehicleCategories.find(c => c.value === category);
    return found ? found.label : 'Unknown';
  }

  getVehicleBodyTypeName(bodyType: VehicleBodyType | null): string {
    if (!bodyType) return 'Unknown';
    
    const found = this.vehicleBodyTypes.find(b => b.value === bodyType);
    return found ? found.label : 'Unknown';
  }
}