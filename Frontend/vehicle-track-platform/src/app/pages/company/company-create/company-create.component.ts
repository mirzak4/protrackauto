import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Company } from '../company.model';
import { CompanyService } from '../company.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-company-create',
  templateUrl: './company-create.component.html',
  imports: [CommonModule, FormsModule, RouterModule], 
  styleUrls: ['./company-create.component.css']
})
export class CompanyCreateComponent {
  company: Company = {
    name: '',
    address: '',
    phoneNumber: '',
    email: '',
    contactPerson: '',
    companyType: 'INSURANCE_COMPANY'
  };

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) {}

  createCompany(): void {
    this.companyService.createCompany(this.company).subscribe({
      next: () => this.router.navigate(['/company']),
      error: (err) => console.error('Error creating company', err)
    });
  }
}
