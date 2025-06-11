import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FuelPrice } from '../models/fuel-price.model';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuelPriceService {
  private apiUrl = `${environment.apiUrl}/api/fuel-price`;

  constructor(private http: HttpClient) {}

  getAllFuelPrices(): Observable<FuelPrice[]> {
    return this.http.get<FuelPrice[]>(this.apiUrl);
  }

  getCompanyFuelPrices(companyId: number): Observable<FuelPrice[]> {
    return this.http.get<FuelPrice[]>(`${this.apiUrl}/company/${companyId}`);
  }

  getFuelPriceById(id: number): Observable<FuelPrice> {
    return this.http.get<FuelPrice>(`${this.apiUrl}/${id}`);
  }

  createFuelPrice(fuelPrice: FuelPrice): Observable<FuelPrice> {
    return this.http.post<FuelPrice>(this.apiUrl, fuelPrice);
  }

  updateFuelPrice(id: number, fuelPrice: FuelPrice): Observable<FuelPrice> {
    return this.http.put<FuelPrice>(`${this.apiUrl}/${id}`, fuelPrice);
  }

  deleteFuelPrice(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
