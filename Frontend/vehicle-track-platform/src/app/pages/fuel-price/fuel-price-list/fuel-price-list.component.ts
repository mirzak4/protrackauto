import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FuelService } from 'app/core/services/fuel.service';
import { FuelPriceService } from 'app/core/services/fuel-price.service';
import { CompanyService } from 'app/core/services/company.service';
import { Fuel } from 'app/core/models/fuel.model';
import { FuelPrice } from 'app/core/models/fuel-price.model';
import { CompanyDTO, CompanyType } from 'app/core/models/company.model';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
    selector: 'app-fuel-price-list',
    standalone: true,
    imports: [CommonModule, MatSnackBarModule],
    templateUrl: './fuel-price-list.component.html',
    styleUrls: ['./fuel-price-list.component.css']
})
export class FuelPriceListComponent implements OnInit {
    fuels: Fuel[] = [];
    fuelPrices: FuelPrice[] = [];
    gasStations: CompanyDTO[] = [];
    isDeleteConfirmOpen = false;
    fuelPriceToDelete: FuelPrice | null = null;
    fuelMap: { [key: number]: string } = {};
    gasStationMap: { [key: number]: string } = {};

    constructor(
        private fuelService: FuelService,
        private fuelPriceService: FuelPriceService,
        private companyService: CompanyService,
        private router: Router,
        private snackBar: MatSnackBar
    ) { }

    ngOnInit(): void {
        this.loadData();
    }

    loadData(): void {
        this.fuelService.getAllFuels().subscribe({
            next: fuels => {
                this.fuels = fuels;
                // Create fuel map for quick lookup
                fuels.forEach(fuel => this.fuelMap[fuel.id] = fuel.name);
            },
            error: (error) => {
                console.error('Error loading fuels:', error);
            }
        });

        this.fuelPriceService.getAllFuelPrices().subscribe({
            next: prices => {
                this.fuelPrices = prices;
            },
            error: (error) => {
                console.error('Error loading fuel prices:', error);
            }
        });

        this.companyService.getAll().subscribe({
            next: companies => {
                this.gasStations = companies.filter(c => c.companyType === CompanyType.GAS_STATION);
                // Create gas station map for quick lookup
                this.gasStations.forEach(station => this.gasStationMap[station.id] = station.name);
            },
            error: (error) => {
                console.error('Error loading companies:', error);
            }
        });
    }

    getFuelPricesByFuel(fuelId: number): FuelPrice[] {
        return this.fuelPrices.filter(fp => fp.fuelId === fuelId);
    }

    getCompanyName(companyId: number): string {
        return this.gasStationMap[companyId] || 'Unknown Station';
    }

    getFuelName(fuelId: number): string {
        return this.fuelMap[fuelId] || 'Unknown Fuel';
    }

    addFuelPrice() {
        this.router.navigate(['/fuel-prices/new']);
    }

    editFuelPrice(fuelPriceId: number) {
        this.router.navigate(['/fuel-prices/edit', fuelPriceId]);
    }

    deleteFuelPrice(fuelPrice: FuelPrice) {
        this.fuelPriceToDelete = fuelPrice;
        this.isDeleteConfirmOpen = true;
    }

    confirmDelete() {
        if (!this.fuelPriceToDelete?.id) return;

        this.fuelPriceService.deleteFuelPrice(this.fuelPriceToDelete.id).subscribe({
            next: () => {
                this.loadData(); // Reload all data to refresh the view
                this.isDeleteConfirmOpen = false;
                this.snackBar.open('Fuel price deleted successfully.', 'Dismiss', {
                    duration: 3500,
                    panelClass: ['snackbar-success']
                });
                this.fuelPriceToDelete = null;
            },
            error: (error) => {
                console.error('Error deleting fuel price:', error);
                this.isDeleteConfirmOpen = false;
                this.fuelPriceToDelete = null;

                this.snackBar.open('Error deleting fuel price. Please try again.', 'Close', {
                    duration: 3000,
                    panelClass: ['snackbar-error']
                });
            }
        });
    }

    cancelDelete() {
        this.isDeleteConfirmOpen = false;
        this.fuelPriceToDelete = null;
    }

    formatDate(dateString: string): string {
        const date = new Date(dateString);
        return date.toLocaleDateString();
    }

    formatPrice(price: number): string {
        return `$${price.toFixed(2)}`;
    }

    // Helper method to get the latest price for a fuel at a specific station
    getLatestPrice(fuelId: number, stationId: number): FuelPrice | null {
        const prices = this.fuelPrices.filter(fp => fp.fuelId === fuelId && fp.gasStationId === stationId);
        if (prices.length === 0) return null;

        return prices.reduce((latest, current) => {
            return new Date(current.issueDate) > new Date(latest.issueDate) ? current : latest;
        });
    }

    // Helper method to check if a fuel price is the most recent for that fuel/station combination
    isLatestPrice(fuelPrice: FuelPrice): boolean {
        const latestPrice = this.getLatestPrice(fuelPrice.fuelId, fuelPrice.gasStationId);
        return latestPrice?.id === fuelPrice.id;
    }

    // Get fuel prices for a specific company (useful if you want to filter by gas station)
    loadFuelPricesByCompany(companyId: number): void {
        this.fuelPriceService.getCompanyFuelPrices(companyId).subscribe({
            next: prices => {
                this.fuelPrices = prices;
            },
            error: (error) => {
                console.error('Error loading company fuel prices:', error);
                this.snackBar.open('Error loading fuel prices for this station.', 'Close', {
                    duration: 3000,
                    panelClass: ['snackbar-error']
                });
            }
        });
    }

    // Sort fuel prices by date (newest first)
    getSortedFuelPrices(): FuelPrice[] {
        return [...this.fuelPrices].sort((a, b) =>
            new Date(b.issueDate).getTime() - new Date(a.issueDate).getTime()
        );
    }
}