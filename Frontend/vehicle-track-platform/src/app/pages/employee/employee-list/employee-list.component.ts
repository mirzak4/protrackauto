import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmployeeService } from 'app/core/services/employee.service';
import { Employee } from 'app/core/models/employee.model';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RoleService } from 'app/core/services/role.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  templateUrl: './employee-list.component.html',
  imports: [CommonModule, MatSnackBarModule],
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  isDeleteConfirmOpen = false;
  employeeToDelete: Employee | null = null;
  roleMap: { [key: number]: string } = {};
  
  currentPage = 1;
  itemsPerPage = 10;


  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private snackBar: MatSnackBar,
    private roleService: RoleService
  ) {}

  ngOnInit() {
    this.loadEmployees();
    this.loadRoles();
  }

  loadRoles() {
    this.roleService.getAllRoles().subscribe({
      next: (roles) => {
        roles.forEach(role => this.roleMap[role.id] = role.name);
      },
      error: () => {
        console.error('Error loading roles');
      }
    });
  }

  loadEmployees() {
    this.employeeService.getAllEmployees().subscribe({
      next: (employees) => {
        this.employees = employees;
      },
      error: (error) => {
        console.error('Error loading employees:', error);
      }
    });
  }

  get pagedEmployees(): Employee[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return this.employees.slice(start, start + this.itemsPerPage);
  }

  get totalPages(): number {
    return Math.ceil(this.employees.length / this.itemsPerPage);
  }

  changePage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
  

  addEmployee() {
    this.router.navigate(['/employees/new']);
  }

  editEmployee(employeeId: number) {
    this.router.navigate(['/employees/edit', employeeId]);
  }

  deleteEmployee(employee: Employee) {
    this.employeeToDelete = employee;
    this.isDeleteConfirmOpen = true;
  }

  confirmDelete() {
    if (!this.employeeToDelete?.id) return;

    this.employeeService.deleteEmployee(this.employeeToDelete.id).subscribe({
      next: () => {
        this.loadEmployees();
        this.isDeleteConfirmOpen = false;
        this.snackBar.open('Employee deleted successfully.', 'Dismiss', {
          duration: 3500,
          panelClass: ['snackbar-success']
        });
        this.employeeToDelete = null;
      },
      error: (error) => {
        console.error('Error deleting employee:', error);
        this.isDeleteConfirmOpen = false;
        this.employeeToDelete = null;

        this.snackBar.open('Error deleting employee. Please try again.', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
      }
    });
  }

  cancelDelete() {
    this.isDeleteConfirmOpen = false;
    this.employeeToDelete = null;
  }

  getFullName(user: any): string {
    return user ? `${user.firstName} ${user.lastName}` : '';
  }

  formatRoleName(roleName: string): string {
    if (!roleName) return '';
    const words = roleName.split('_');
    const formattedWords = words.map(word => 
      word.charAt(0).toUpperCase() + word.slice(1).toLowerCase()
    );
    return formattedWords.join(' ');
  }

}
