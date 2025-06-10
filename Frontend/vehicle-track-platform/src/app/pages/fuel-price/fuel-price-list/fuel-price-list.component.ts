import { Component, OnInit } from '@angular/core';
import { FuelService } from 'app/core/services/fuel.service';
import { FuelPriceService } from 'app/core/services/fuel-price.service';
import { CompanyService } from 'app/core/services/company.service';
import { Fuel } from 'app/core/models/fuel.model';
import { FuelPrice } from 'app/core/models/fuel-price.model';
import { CompanyDTO, CompanyType } from 'app/core/models/company.model';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-fuel-price-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './fuel-price-list.component.html',
    styleUrls: ['./fuel-price-list.component.css']
})
export class FuelPriceListComponent implements OnInit {
    fuels: Fuel[] = [];
    fuelPrices: FuelPrice[] = [];
    gasStations: CompanyDTO[] = [];

    constructor(
        private fuelService: FuelService,
        private fuelPriceService: FuelPriceService,
        private companyService: CompanyService
    ) { }

    ngOnInit(): void {
        this.loadData();
    }

    loadData(): void {
        this.fuelService.getAllFuels().subscribe({
            next: fuels => this.fuels = fuels
        });

        this.fuelPriceService.getAllFuelPrices().subscribe({
            next: prices => this.fuelPrices = prices
        });

        this.companyService.getAll().subscribe({
            next: companies => {
                this.gasStations = companies.filter(c => c.companyType === CompanyType.GAS_STATION);
            }
        });

        // // Mock Fuels
        // const fuels = [
        //     { id: 1, name: 'Gasoline' },
        //     { id: 2, name: 'Diesel' },
        //     { id: 3, name: 'Electric' },
        // ];

        // // Mock Companies (Gas Stations)
        // const companies = [
        //     { id: 101, name: 'Shell Station', address: '123 Main St', phoneNumber: '555-1234', companyType: 3, contactPerson: 'Alice' },
        //     { id: 102, name: 'BP Station', address: '456 Oak Rd', phoneNumber: '555-5678', companyType: 3, contactPerson: 'Bob' },
        //     { id: 103, name: 'Exxon Station', address: '789 Pine Ave', phoneNumber: '555-9012', companyType: 3, contactPerson: 'Charlie' },
        // ];

        // // Mock Fuel Prices
        // const fuelPrices = [
        //     { id: 1001, price: 3.59, issueDate: '2025-06-01', fuelId: 1, gasStationId: 101 },
        //     { id: 1002, price: 3.49, issueDate: '2025-06-02', fuelId: 1, gasStationId: 102 },
        //     { id: 1003, price: 3.55, issueDate: '2025-06-03', fuelId: 1, gasStationId: 103 },
        //     { id: 1004, price: 3.19, issueDate: '2025-06-01', fuelId: 2, gasStationId: 101 },
        //     { id: 1005, price: 3.15, issueDate: '2025-06-02', fuelId: 2, gasStationId: 102 },
        //     { id: 1006, price: 0.12, issueDate: '2025-06-01', fuelId: 3, gasStationId: 103 },
        // ];
        // this.fuels = fuels;
        // this.gasStations = companies;
        // this.fuelPrices = fuelPrices;
    }

    getFuelPricesByFuel(fuelId: number): FuelPrice[] {
        return this.fuelPrices.filter(fp => fp.fuelId === fuelId);
    }

    getCompanyName(companyId: number): string {
        return this.gasStations.find(c => c.id === companyId)?.name || 'Unknown Station';
    }
}
