import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { VehicleService } from 'app/core/services/vehicle.service';
import { Vehicle, VehicleCategory, VehicleBodyType } from 'app/core/models/vehicle.model';
import { Fuel } from 'app/core/models/fuel.model';
import { FuelService } from 'app/core/services/fuel.service';

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
  fuels: Fuel[] = [];

  vehicle: Vehicle = {
    id: 0,
    licensePlate: '',
    vehicleBrandType: '',
    productionYear: new Date().getFullYear(),
    vehicleCategory: null,
    vehicleBodyType: null,
    fuelId: 0,
    firstRegistrationDate: '',
    firstRegistrationPlace: '',
    firstLicensePlate: '',
    registrationIssueDate: '',
    registrationIssuePlace: '',
    color: '',
    registrationNumber: '',
    commercialDescription: '',
    chassisNumber: '',
    maxWeight: 0,
    payload: 0,
    vehicleWeight: 0,
    powerWeightRatio: 0,
    seatCount: 0,
    engineDisplacement: 0,
    maxPower: 0,
    ecoCharacteristics: '',
    catalyst: '',
    engineNumber: ''
  };

  vehicleCategories = [
    { value: VehicleCategory.TRAVEL_CAR, label: 'Travel Car' },
    { value: VehicleCategory.TRUCK, label: 'Truck' },
    { value: VehicleCategory.MOTORCYCLE, label: 'Motorcycle' },
    { value: VehicleCategory.BUS, label: 'Bus' },
    { value: VehicleCategory.VAN, label: 'Van' },
    { value: VehicleCategory.SUV, label: 'SUV' },
    { value: VehicleCategory.PICKUP, label: 'Pickup' },
    { value: VehicleCategory.TRACTOR, label: 'Tractor' },
    { value: VehicleCategory.SCOOTER, label: 'Scooter' },
    { value: VehicleCategory.BICYCLE, label: 'Bicycle' }
  ];

  vehicleBodyTypes = [
    { value: VehicleBodyType.SEDAN, label: 'Sedan' },
    { value: VehicleBodyType.HATCHBACK, label: 'Hatchback' },
    { value: VehicleBodyType.SUV, label: 'SUV' },
    { value: VehicleBodyType.COUPE, label: 'Coupe' },
    { value: VehicleBodyType.CONVERTIBLE, label: 'Convertible' },
    { value: VehicleBodyType.WAGON, label: 'Wagon' },
    { value: VehicleBodyType.PICKUP, label: 'Pickup' },
    { value: VehicleBodyType.VAN, label: 'Van' },
    { value: VehicleBodyType.MINIVAN, label: 'Minivan' },
    { value: VehicleBodyType.ROADSTER, label: 'Roadster' },
    { value: VehicleBodyType.JEEP, label: 'Jeep' },
    { value: VehicleBodyType.LIMOUSINE, label: 'Limousine' },
    { value: VehicleBodyType.TRUCK, label: 'Truck' },
    { value: VehicleBodyType.BUS, label: 'Bus' },
    { value: VehicleBodyType.MOTORCYCLE , label: 'Motorcycle' }
  ];

  constructor(
    private vehicleService: VehicleService,
    private fuelService: FuelService,
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

    this.loadFuels();  
  }

  loadFuels() {
    this.fuelService.getAllFuels().subscribe({
      next: (data) => {
        this.fuels = data;  
      },
      error: (err) => {
        console.error('Error loading fuels:', err);
        this.snackBar.open('Error loading fuel data', 'Close', { duration: 3000 });
      }
    });
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