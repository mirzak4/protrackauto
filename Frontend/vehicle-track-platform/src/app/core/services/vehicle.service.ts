import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VehicleDTO } from '../models/vehicle.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAllVehicles(): Observable<VehicleDTO[]> {
    return this.http.get<VehicleDTO[]>(`${environment.apiUrl}/api/vehicle`);
  }

  getVehicleById(id: number): Observable<VehicleDTO> {
    return this.http.get<VehicleDTO>(`${environment.apiUrl}/api/vehicle/${id}`);
  }

  createVehicle(vehicleData: Omit<VehicleDTO, 'id'>): Observable<VehicleDTO> {
    return this.http.post<VehicleDTO>(`${environment.apiUrl}/api/vehicle`, vehicleData);
  }

  updateVehicle(id: number, vehicleData: VehicleDTO): Observable<VehicleDTO> {
    return this.http.put<VehicleDTO>(`${environment.apiUrl}/api/vehicle/${id}`, vehicleData);
  }

  deleteVehicle(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/vehicle/${id}`);
  }
}