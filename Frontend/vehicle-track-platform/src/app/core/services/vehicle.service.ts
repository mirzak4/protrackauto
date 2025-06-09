import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from '../models/vehicle.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAllVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${environment.apiUrl}/api/vehicle`);
  }
  
  getVehicleById(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(`${environment.apiUrl}/api/vehicle/${id}`);
  }

  createVehicle(vehicleData: Omit<Vehicle, 'id'>): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${environment.apiUrl}/api/vehicle`, vehicleData);
  }

  updateVehicle(id: number, vehicleData: Vehicle): Observable<Vehicle> {
    return this.http.put<Vehicle>(`${environment.apiUrl}/api/vehicle/${id}`, vehicleData);
  }

  deleteVehicle(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/vehicle/${id}`);
  }
}