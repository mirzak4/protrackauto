import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO, CompanyType } from 'app/core/models/company.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-company-form',
  standalone: true,
  templateUrl: './company-form.component.html',
  imports: [FormsModule, MatSnackBarModule, CommonModule],
  styleUrls: ['./company-form.component.css']
})
export class CompanyFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;

  CompanyType = CompanyType; 

  company: CompanyDTO = {
    id: 0,
    name: '',
    address: '',
    phoneNumber: '',
    email: '',
    companyType: CompanyType.INSURANCE_COMPANY, 
    contactPerson: ''
  };

  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    public router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadCompany(+id);
    }
  }

  onInputChange() {
    this.hasChanges = true;
  }

  loadCompany(id: number) {
    this.companyService.getById(id).subscribe({
      next: (company) => {
        this.company = company;
      },
      error: (error) => {
        console.error('Error loading company:', error);
      }
    });
  }

  saveCompany() {
    if (!this.isFormValid()) return;
    
    if (!this.isEditMode && !this.company.companyType) {
      this.snackBar.open('Please select company type', 'Close', { duration: 3000 });
      return;
    }

    const request = this.isEditMode
      ? this.companyService.updateCompany(this.company.id!, this.company)
      : this.companyService.createCompany(this.company);

    request.subscribe({
      next: () => {
        this.snackBar.open(
          this.isEditMode ? 'Company updated successfully.' : 'Company created successfully.',
          'Dismiss',
          { duration: 3500, panelClass: ['snackbar-success'] }
        );
        this.hasChanges = false;
        this.router.navigate(['/companies']);
      },
      error: (error) => {
        console.error('Error saving company:', error);
        this.snackBar.open('Error saving company. Please try again.', 'Close', { duration: 3000 });
      }
    });
  }

  isFormValid(): boolean {
    return !!(
      this.company.name &&
      this.company.address &&
      this.company.email &&
      this.company.phoneNumber &&
      (this.isEditMode || this.company.companyType) 
    );
  }

  getCompanyTypeName(type: CompanyType): string {
    switch(type) {
      case CompanyType.INSURANCE_COMPANY: return 'Insurance Company';
      case CompanyType.SERVICE_PROVIDER: return 'Service Provider';
      case CompanyType.GAS_STATION: return 'Gas Station';
      default: return 'Unknown';
    }
  }

}