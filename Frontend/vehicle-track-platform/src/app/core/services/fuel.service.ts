import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Fuel } from '../models/fuel.model';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuelService {
  private apiUrl = `${environment.apiUrl}/api/fuel`;

  constructor(private http: HttpClient) {}

  getAllFuels(): Observable<Fuel[]> {
    return this.http.get<Fuel[]>(this.apiUrl);
  }

  getFuelById(id: number): Observable<Fuel> {
    return this.http.get<Fuel>(`${this.apiUrl}/${id}`);
  }

  createFuel(fuel: Fuel): Observable<number> {
    return this.http.post<number>(this.apiUrl, fuel);
  }

  updateFuel(id: number, fuel: Fuel): Observable<Fuel> {
    return this.http.put<Fuel>(`${this.apiUrl}/${id}`, fuel);
  }

  deleteFuel(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
