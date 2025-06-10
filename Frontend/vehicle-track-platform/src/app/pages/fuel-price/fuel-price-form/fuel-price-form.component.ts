import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FuelPriceService } from 'app/core/services/fuel-price.service';
import { FuelService } from 'app/core/services/fuel.service';
import { CompanyService } from 'app/core/services/company.service';
import { Fuel } from 'app/core/models/fuel.model';
import { FuelPrice } from 'app/core/models/fuel-price.model';
import { CompanyDTO } from 'app/core/models/company.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';

@Component({
    selector: 'app-fuel-price-form',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './fuel-price-form.component.html',
    styleUrls: ['./fuel-price-form.component.css']
})
export class FuelPriceFormComponent implements OnInit {
    isEditMode = false;
    hasChanges = false;
    isLoading = true;

    fuels: Fuel[] = [];
    gasStations: CompanyDTO[] = [];

    fuelPrice: FuelPrice = {
        id: 0,
        fuelId: 0,
        gasStationId: 0,
        price: 0,
        issueDate: ''
    };

    constructor(
        private fuelPriceService: FuelPriceService,
        private fuelService: FuelService,
        private companyService: CompanyService,
        private route: ActivatedRoute,
        public router: Router,
        private snackBar: MatSnackBar,
        private cdr: ChangeDetectorRef
    ) { }

    ngOnInit(): void {
        this.loadInitialData();
    }

    onInputChange() {
        this.hasChanges = true;
    }

    private loadInitialData() {
        forkJoin({
            fuels: this.fuelService.getAllFuels(),
            companies: this.companyService.getAll()
        }).subscribe({
            next: ({ fuels, companies }) => {
                this.fuels = fuels;
                this.gasStations = companies.filter(c => c.companyType === 3); // GAS_STATION

                const id = this.route.snapshot.paramMap.get('id');
                this.isEditMode = !!(id && id !== 'new');

                if (this.isEditMode && id) {
                    this.loadFuelPrice(+id);
                } else {
                    this.fuelPrice.issueDate = this.formatDateForInput(new Date().toISOString());
                    if (this.fuels.length > 0) this.fuelPrice.fuelId = this.fuels[0].id;
                    if (this.gasStations.length > 0) this.fuelPrice.gasStationId = this.gasStations[0].id;
                }

                this.isLoading = false;
                this.cdr.detectChanges();
            },
            error: (err) => {
                console.error('Error loading initial data:', err);
                this.isLoading = false;
                this.cdr.detectChanges();
            }
        });
    }


    private loadFuelPrice(id: number) {
        this.fuelPriceService.getFuelPriceById(id).subscribe({
            next: (fp) => {
                this.fuelPrice = {
                    ...fp,
                    issueDate: this.formatDateForInput(fp.issueDate)
                };
                this.cdr.detectChanges();
            },
            error: (err) => {
                console.error('Error loading fuel price:', err);
            }
        });
    }

    saveFuelPrice() {
        if (!this.isFormValid()) return;

        const payload: FuelPrice = {
            ...this.fuelPrice,
            issueDate: new Date(this.fuelPrice.issueDate).toISOString()
        };

        const request = this.isEditMode
            ? this.fuelPriceService.updateFuelPrice(this.fuelPrice.id!, payload)
            : this.fuelPriceService.createFuelPrice(payload);

        request.subscribe({
            next: () => {
                this.snackBar.open(
                    this.isEditMode ? 'Fuel price updated!' : 'Fuel price created!',
                    'OK',
                    { duration: 3000 }
                );
                this.hasChanges = false;
                this.router.navigate(['/fuel-prices']);
            },
            error: (error) => {
                console.error('Error saving fuel price:', error);
                this.snackBar.open(
                    `Error: ${error.error?.message || 'Failed to save fuel price'}`,
                    'Close',
                    { duration: 5000 }
                );
            }
        });
    }

    isFormValid(): boolean {
        return !!(
            this.fuelPrice.fuelId &&
            this.fuelPrice.gasStationId &&
            this.fuelPrice.price > 0 &&
            this.fuelPrice.issueDate
        );
    }

    formatDateForInput(dateStr: string): string {
        try {
            return new Date(dateStr).toISOString().split('T')[0];
        } catch {
            return '';
        }
    }
}
