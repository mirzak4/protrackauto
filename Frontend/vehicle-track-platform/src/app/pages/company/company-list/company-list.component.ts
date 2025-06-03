import { Component, OnInit } from '@angular/core';
import { Company } from '../company.model';
import { CompanyService } from '../company.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  imports: [CommonModule, FormsModule, RouterModule], 
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  companies: Company[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.companyService.getCompanies('').subscribe({
      next: (data) => this.companies = data,
      error: (err) => console.error('Error loading companies', err)
    });
  }

  deleteCompany(id?: number): void {
    if (!id) return;
    if (confirm('Are you sure you want to delete this company?')) {
      this.companyService.deleteCompany(id).subscribe({
        next: () => this.loadCompanies(),
        error: (err) => console.error('Error deleting company', err)
      });
    }
  }
}
