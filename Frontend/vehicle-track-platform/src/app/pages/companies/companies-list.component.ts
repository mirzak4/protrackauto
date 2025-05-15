import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO } from 'app/core/models/company.model';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-companies-list',
  templateUrl: './companies-list.component.html',
  imports: [CommonModule, RouterModule]
})
export class CompaniesListComponent implements OnInit {
  companies: CompanyDTO[] = [];

  private companyService = inject(CompanyService);

  ngOnInit() {
    this.loadCompanies();
  }

  loadCompanies() {
    this.companyService.getAll().subscribe(data => {
      this.companies = data;
    });
  }

  deleteCompany(id: number) {
    this.companyService.delete(id).subscribe(() => {
      this.loadCompanies();
    });
  }
}
