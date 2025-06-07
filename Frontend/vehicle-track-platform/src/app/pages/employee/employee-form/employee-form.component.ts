import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from 'app/core/services/employee.service';
import { CompanyService } from 'app/core/services/company.service';
import { Employee } from 'app/core/models/employee.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { Role } from 'app/core/models/role.model';
import { RoleService } from 'app/core/services/role.service';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-employee-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  isEditMode = false;
  hasChanges = false;
  filteredRoles: Role[] = [];
  allCompanies: {id: number, name: string, type: string}[] = [];
  isLoading = true;
  originalEmployee: Employee | null = null;

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
    private snackBar: MatSnackBar,
    private roleService: RoleService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadInitialData();
  }

  onInputChange() {
    this.hasChanges = true;
  }

  private loadInitialData() {
    forkJoin([
      this.roleService.getAllRoles(),
      this.companyService.getAll('1'),
      this.companyService.getAll('2'),
      this.companyService.getAll('3')
    ]).subscribe({
      next: ([roles, insurances, services, stations]) => {
        this.filteredRoles = roles.filter(r =>
          ['CLAIMS_ADJUSTER', 'STATION_MANAGER', 'FIELD_TECHNICIAN'].includes(r.name)
        );

        this.allCompanies = [
          ...insurances.map(c => ({...c, type: '1'})),
          ...services.map(c => ({...c, type: '2'})),
          ...stations.map(c => ({...c, type: '3'}))
        ];

        const id = this.route.snapshot.paramMap.get('id');
        this.isEditMode = !!(id && id !== 'new');

        if (this.isEditMode && id) {
          this.loadEmployee(+id);
        } else {
          if (this.filteredRoles.length > 0) {
            this.employee.user.roleId = this.filteredRoles[0].id;
            this.filterCompaniesByRole(this.employee.user.roleId);
          }
        }

        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error loading initial data:', error);
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  private filterCompaniesByRole(roleId: number) {
    const selectedRole = this.filteredRoles.find(r => r.id === roleId);
    if (!selectedRole) {
      this.companies = [];
      return;
    }

    let companyType = '';
    switch (selectedRole.name) {
      case 'STATION_MANAGER': companyType = '3'; break;
      case 'FIELD_TECHNICIAN': companyType = '2'; break;
      case 'CLAIMS_ADJUSTER': companyType = '1'; break;
    }

    this.companies = this.allCompanies
      .filter(c => c.type === companyType)
      .map(({id, name}) => ({id, name}));


    this.hasChanges = true;
    this.cdr.detectChanges();
  }

  onRoleChange(newRoleId: number) {
    this.hasChanges = true;
    this.filterCompaniesByRole(newRoleId);
  }

  loadEmployee(id: number) {
    this.employeeService.getEmployeeById(id).subscribe({
      next: (emp) => {
        this.employee = JSON.parse(JSON.stringify(emp));
        if (emp.user?.birthDate) {
          this.employee.user.birthDate = this.formatDateForInput(emp.user.birthDate);
        }
        this.filterCompaniesByRole(emp.user.roleId);
      },
      error: (error) => {
        console.error('Error loading employee:', error);
      }
    });
  }

  saveEmployee() {
    if (!this.isFormValid()) return;
    const employeeToSave = {
      ...this.employee,
      companyId: this.employee.companyId,
      user: {
        ...this.employee.user,
        roleId: this.employee.user.roleId,
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
          this.isEditMode ? 'Employee updated successfully!' : 'Employee created successfully!',
          'OK',
          { duration: 3000 }
        );
        this.hasChanges = false;
        this.router.navigate(['/employees']);
      },
      error: (error) => {
        console.error('Error saving employee:', error);
        this.snackBar.open(
          `Error: ${error.error?.message || 'Failed to save employee'}`,
          'Close',
          { duration: 5000 }
        );
      }
    });
  }

  isFormValid(): boolean {
    const u = this.employee.user;
    return !!(
      this.employee.companyId &&
      u.firstName?.trim() &&
      u.lastName?.trim() &&
      u.email?.trim() &&
      u.phoneNumber?.trim() &&
      u.roleId
    );
  }

  formatDateForInput(dateStr: string): string {
    if (!dateStr) return '';
    try {
      return new Date(dateStr).toISOString().split('T')[0];
    } catch {
      return '';
    }
  }
}