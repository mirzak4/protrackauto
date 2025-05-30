import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fuel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './fuel.component.html',
  styleUrls: ['./fuel.component.css']
})
export class FuelComponent {
  fuelRecords = [
    { date: '2025-01-01', vehicle: 'Truck A', volume: 50, cost: 120.5 },
    { date: '2025-01-05', vehicle: 'Van B', volume: 30, cost: 75.2 },
    { date: '2025-01-12', vehicle: 'Truck C', volume: 40, cost: 98.7 }
  ];

  getTotalCost(): number {
    return this.fuelRecords.reduce((sum, record) => sum + record.cost, 0);
  }
}
