import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BaseChartDirective } from 'ng2-charts';
import {
  ChartConfiguration,
  ChartType,
  ChartOptions,
} from 'chart.js';

@Component({
  standalone: true,
  selector: 'app-fuel',
  imports: [CommonModule, BaseChartDirective],
  templateUrl: './fuel.component.html',
  styleUrls: ['./fuel.component.css']
})
export class FuelComponent {
  // ✅ Sample fuel records (replace with actual array later)
  fuelRecords = [
    { vehicle: 'Toyota Corolla', date: '2024-05-01', liters: 50, cost: 90 },
    { vehicle: 'Ford Transit', date: '2024-05-03', liters: 70, cost: 120 },
    { vehicle: 'Tesla Model 3', date: '2024-05-05', liters: 15, cost: 30 }
  ];

  // ✅ Correct ChartData shape
  public barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: this.fuelRecords.map(r => r.vehicle),
    datasets: [
      {
        data: this.fuelRecords.map(r => r.cost),
        label: 'Fuel Cost ($)'
      }
    ]
  };

  public barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    plugins: {
      legend: { display: true }
    }
  };

  public barChartType: 'bar' = 'bar';

  getTotalCost(): number {
    return this.fuelRecords.reduce((sum, r) => sum + r.cost, 0);
  }
}
