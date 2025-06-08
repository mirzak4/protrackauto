import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO } from 'app/core/models/company.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-company-list',
  standalone: true,
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css'],
  imports: [CommonModule, MatSnackBarModule, FormsModule]
})
export class CompanyListComponent implements OnInit {
  companies: CompanyDTO[] = [];
  isDeleteConfirmOpen = false;
  companyToDelete: CompanyDTO | null = null;
  selectedCompanyType: number = 1;

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.loadCompanies();
  }

  loadCompanies() {
    this.companyService.getAll(this.selectedCompanyType.toString()).subscribe({
      next: (data) => {
        this.companies = data;
      },
      error: (err) => {
        console.error('Error loading companies:', err);
      }
    });
  }

  onCompanyTypeChange() {
    this.loadCompanies();
  }

  addCompany() {
    this.router.navigate(['/companies/new']);
  }

  editCompany(id: number) {
    this.router.navigate(['/companies/edit', id]);
  }

  deleteCompany(company: CompanyDTO) {
    this.companyToDelete = company;
    this.isDeleteConfirmOpen = true;
  }

  confirmDelete() {
    if (!this.companyToDelete?.id) return;

    this.companyService.deleteCompany(this.companyToDelete.id).subscribe({
      next: () => {
        this.loadCompanies();
        this.isDeleteConfirmOpen = false;
        this.snackBar.open('Company deleted successfully.', 'Dismiss', {
          duration: 3500,
          panelClass: ['snackbar-success']
        });
        this.companyToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting company:', error);
        this.isDeleteConfirmOpen = false;
        this.companyToDelete = null;
        this.snackBar.open('Error deleting company. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.companyToDelete = null;
  }
}
