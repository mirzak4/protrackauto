import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from 'app/core/services/employee.service';
import { CompanyService } from 'app/core/services/company.service';
import { Employee } from 'app/core/models/employee.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  imports: [FormsModule],
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;

  employee: Employee = {
    companyId: 0,
    userId: 0,
    user: {
      id: 0,
      firstName: '',
      lastName: '',
      email: '',
      phoneNumber: '',
      birthDate: '',
      roleId: 0 
    }
  };

  companies: { id: number; name: string }[] = [];

  constructor(
    private employeeService: EmployeeService,
    private companyService: CompanyService,
    private route: ActivatedRoute,
    public router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.loadCompanies();

    const id = this.route.snapshot.paramMap.get('id');
    if (id && id !== 'new') {
      this.isEditMode = true;
      this.loadEmployee(+id);
    }
  }

  loadCompanies() {
    this.companyService.getAllCompanies().subscribe((data) => {
      this.companies = data;
    });
  }

  loadEmployee(id: number) {
    this.employeeService.getEmployeeById(id).subscribe((emp) => {
      this.employee = emp;
      if (emp.user && emp.user.birthDate) {
        this.employee.user.birthDate = this.formatDateForInput(emp.user.birthDate);
      }
    });
  }

  onInputChange() {
    this.hasChanges = true;
  }

  saveEmployee() {
    if (!this.isFormValid()) return;

    const employeeToSave = {
      ...this.employee,
      user: {
        ...this.employee.user,
        birthDate: this.employee.user.birthDate
          ? new Date(this.employee.user.birthDate).toISOString()
          : ''
      }
    };

    const request = this.isEditMode
      ? this.employeeService.updateEmployee(this.employee.id!, employeeToSave)
      : this.employeeService.createEmployee(employeeToSave);

    request.subscribe({
      next: () => {
        this.snackBar.open(
          this.isEditMode ? 'Employee updated.' : 'Employee created.',
          'OK',
          { duration: 3000 }
        );
        this.hasChanges = false;
        this.router.navigate(['/employees']);
      },
      error: () => {
        this.snackBar.open('Error saving employee.', 'Close', { duration: 3000 });
      }
    });
  }

  isFormValid(): boolean {
    const u = this.employee.user;
    return !!(
      this.employee.companyId &&
      this.employee.user?.firstName &&
      this.employee.user?.lastName &&
      this.employee.user?.email &&
      this.employee.user?.phoneNumber &&
      this.employee.user?.roleId
    );
  }

  formatDateForInput(dateStr: string): string {
    return dateStr ? new Date(dateStr).toISOString().split('T')[0] : '';
  }
}
