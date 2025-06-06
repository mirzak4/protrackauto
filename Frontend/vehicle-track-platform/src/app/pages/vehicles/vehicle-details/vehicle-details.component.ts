import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleService } from 'app/core/services/vehicle.service';
import { VehicleDTO } from 'app/core/models/vehicle.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  imports: [CommonModule],
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {
  vehicle: VehicleDTO | null = null;
  isLoading = true;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadVehicle(+id);
    }
  }

  loadVehicle(id: number) {
    this.vehicleService.getVehicleById(id).subscribe({
      next: (vehicle) => {
        this.vehicle = vehicle;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading vehicle:', error);
        this.isLoading = false;
      }
    });
  }

  editVehicle() {
    if (this.vehicle) {
      this.router.navigate(['/vehicles/edit', this.vehicle.id]);
    }
  }

  goBack() {
    this.router.navigate(['/vehicles']);
  }
}