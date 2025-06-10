import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrafficFine } from '../models/traffic-fine.model';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TrafficFineService {
  private baseUrl = `${environment.apiUrl}/api/traffic-fine`; 

  constructor(private http: HttpClient) {}

  getAll(): Observable<TrafficFine[]> {
    return this.http.get<TrafficFine[]>(this.baseUrl);
  }

  getById(id: number): Observable<TrafficFine> {
    return this.http.get<TrafficFine>(`${this.baseUrl}/${id}`);
  }

  create(fine: TrafficFine): Observable<TrafficFine> {
    return this.http.post<TrafficFine>(this.baseUrl, fine);
  }

  update(fine: TrafficFine): Observable<TrafficFine> {
    return this.http.put<TrafficFine>(`${this.baseUrl}/${fine.id}`, fine);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
