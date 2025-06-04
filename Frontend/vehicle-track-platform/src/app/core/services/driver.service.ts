import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DriverDTO, UserDTO } from '../models/driver.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private http: HttpClient) { }

  getAllDrivers(): Observable<DriverDTO[]> {
    return this.http.get<DriverDTO[]>(`${environment.apiUrl}/api/driver`);
  }

  getDriverById(id: number): Observable<DriverDTO> {
    return this.http.get<DriverDTO>(`${environment.apiUrl}/api/driver/${id}`);
  }

  createDriver(driverData: Omit<DriverDTO, 'id'>): Observable<DriverDTO> {
    return this.http.post<DriverDTO>(`${environment.apiUrl}/api/driver`, driverData);
  }

  updateDriver(id: number, driverData: DriverDTO): Observable<DriverDTO> {
    return this.http.put<DriverDTO>(`${environment.apiUrl}/api/driver/${id}`, driverData);
  }

  deleteDriver(userId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/driver/${userId}`);
  }
}