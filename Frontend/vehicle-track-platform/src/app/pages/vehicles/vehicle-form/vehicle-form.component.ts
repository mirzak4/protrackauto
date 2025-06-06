import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleDTO, VehicleBodyType, VehicleCategory} from 'app/core/models/vehicle.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vehicle-form',
  templateUrl: './vehicle-form.component.html',
  imports: [FormsModule],
  styleUrls: ['./vehicle-form.component.css']
})
export class VehicleFormComponent implements OnInit {
  isEditMode = false;
  vehicle: Partial<VehicleDTO> = this.createEmptyVehicle();
  vehicleBodyTypes = Object.values(VehicleBodyType);
  vehicleCategories = Object.values(VehicleCategory);
  formChanged = false;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadVehicle(+id);
    }
  }

  createEmptyVehicle(): Partial<VehicleDTO> {
    return {
      licensePlate: '',
      firstRegistrationDate: new Date().toISOString().split('T')[0],
      firstRegistrationPlace: '',
      firstLicensePlate: '',
      registrationIssueDate: new Date().toISOString().split('T')[0],
      registrationIssuePlace: '',
      fuelId: 0,
      vehicleCategory: VehicleCategory.PASSENGER,
      vehicleBodyType: VehicleBodyType.SEDAN,
      color: '',
      vehicleBrandType: '',
      registrationNumber: '',
      commercialDescription: '',
      chassisNumber: '',
      productionYear: new Date().getFullYear(),
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
  }

  loadVehicle(id: number) {
    this.vehicleService.getVehicleById(id).subscribe({
      next: (vehicle) => {
        this.vehicle = {
          ...vehicle,
          firstRegistrationDate: this.formatDateForInput(vehicle.firstRegistrationDate),
          registrationIssueDate: this.formatDateForInput(vehicle.registrationIssueDate)
        };
      },
      error: (error) => {
        console.error('Error loading vehicle:', error);
      }
    });
  }

  onInputChange() {
    this.formChanged = true;
  }

  isFormValid(): boolean {
    return this.formChanged && 
           !!this.vehicle.licensePlate &&
           !!this.vehicle.firstRegistrationDate &&
           !!this.vehicle.registrationIssueDate &&
           !!this.vehicle.vehicleCategory &&
           !!this.vehicle.vehicleBodyType;
  }

  saveVehicle() {
    if (!this.isFormValid()) return;

    const vehicleData: VehicleDTO = {
      id: this.vehicle.id || 0,
      ...this.vehicle,
      firstRegistrationDate: new Date(this.vehicle.firstRegistrationDate || '').toISOString(),
      registrationIssueDate: new Date(this.vehicle.registrationIssueDate || '').toISOString()
    } as VehicleDTO;

    const request = this.isEditMode
      ? this.vehicleService.updateVehicle(vehicleData.id, vehicleData)
      : this.vehicleService.createVehicle(vehicleData);

    request.subscribe({
      next: () => {
        this.router.navigate(['/vehicles']);
      },
      error: (error) => {
        console.error('Error saving vehicle:', error);
      }
    });
  }

  private formatDateForInput(dateString: string): string {
    return dateString.split('T')[0];
  }
}