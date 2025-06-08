import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CompanyService } from 'app/core/services/company.service';
import { ReportService } from 'app/core/services/report.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FuelService } from 'app/core/services/fuel.service';
import { FuelPriceService } from 'app/core/services/fuel-price.service';
import { Fuel } from 'app/core/models/fuel.model';
import { FuelPrice } from 'app/core/models/fuel-price.model';

@Component({
  selector: 'app-fuel-price-management',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './fuel-price-management.component.html',
  styleUrls: ['./fuel-price-management.component.css']
})
export class FuelPriceManagement implements OnInit {
  reports: any[] = [];
  companyId!: number;
  companyName = '';
  isLoading = false;
  isDownloading = false;
  isGeneratingReport = false;

  fuelPriceForm!: FormGroup;
  fuels: Fuel[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private companyService: CompanyService,
    private reportService: ReportService,
    private snackBar: MatSnackBar,
    private fuelService: FuelService,
    private fuelPriceService: FuelPriceService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.companyId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCompany();
    this.loadReports();
    this.loadFuels();
    this.initForm();
  }

  initForm(): void {
    this.fuelPriceForm = this.fb.group({
      fuelId: [null, Validators.required],
      price: [null, [Validators.required, Validators.min(0.01)]],
      issueDate: [null, Validators.required]
    });
  }

  loadCompany(): void {
    this.companyService.getById(this.companyId).subscribe({
      next: (company) => this.companyName = company.name,
      error: () => this.showError('Error loading company details')
    });
  }

  loadReports(): void {
    this.isLoading = true;
    this.companyService.getCompanyReports(this.companyId).subscribe({
      next: (data) => {
        this.reports = data;
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
        this.showError('Error loading reports');
      }
    });
  }

  loadFuels(): void {
    this.fuelService.getAllFuels().subscribe({
      next: (data) => this.fuels = data,
      error: () => this.showError('Error loading fuels')
    });
  }

  submitFuelPrice(): void {
    if (this.fuelPriceForm.invalid) return;

    const payload: FuelPrice = {
      ...this.fuelPriceForm.value,
      gasStationId: this.companyId
    };

    this.fuelPriceService.createFuelPrice(payload).subscribe({
      next: () => {
        this.snackBar.open('Fuel price added', 'Close', { duration: 3000 });
        this.fuelPriceForm.reset();
        this.loadReports(); // Refresh the reports list
      },
      error: () => this.showError('Error adding fuel price')
    });
  }

  downloadReport(documentId: number): void {
    this.isDownloading = true;
    
    this.reportService.getReportDocument(documentId).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `fuel-report-${documentId}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
        this.isDownloading = false;
      },
      error: () => {
        this.isDownloading = false;
        this.showError('Error downloading report');
      }
    });
  }

   generateNewReport(): void {
    try {
      this.companyService.generateWeeklyFuelPriceReport(this.companyId);
      this.snackBar.open('Report generation started', 'Close', { duration: 3000 });
      this.loadReports();
    } catch (error) {
      console.error('Error generating report', error);
      this.snackBar.open('Error generating report', 'Close', { duration: 3000 });
    }
  }

  goBack(): void {
    this.router.navigate(['/companies']);
  }

  private showError(message: string): void {
    this.snackBar.open(message, 'Close', { duration: 3000 });
  }

  trackByDocumentId(index: number, report: any): number {
    return report.documentId;
  }
}