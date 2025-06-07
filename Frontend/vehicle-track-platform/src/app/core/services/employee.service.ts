import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${environment.apiUrl}/api/employee`);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${environment.apiUrl}/api/employee/${id}`);
  }

  createEmployee(employeeData: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${environment.apiUrl}/api/employee`, employeeData);
  }

  updateEmployee(id: number, employeeData: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${environment.apiUrl}/api/employee/${id}`, employeeData);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/employee/${id}`);
  }
}
