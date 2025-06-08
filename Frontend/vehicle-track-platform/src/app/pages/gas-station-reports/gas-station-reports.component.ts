import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CompanyService } from 'app/core/services/company.service';
import { ReportService } from 'app/core/services/report.service';

@Component({
  selector: 'app-gas-station-reports',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gas-station-reports.component.html',
  styleUrls: ['./gas-station-reports.component.css']
})
export class GasStationReportsComponent implements OnInit {
  reports: any[] = [];
  companyId!: number;
  companyName = '';
  isLoading = false;
  isDownloading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private companyService: CompanyService,
    private reportService: ReportService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.companyId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCompany();
    this.loadReports();
  }

  loadCompany(): void {
    this.companyService.getById(this.companyId).subscribe({
      next: (company) => this.companyName = company.name,
      error: (error) => this.showError('Error loading company details')
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
}

