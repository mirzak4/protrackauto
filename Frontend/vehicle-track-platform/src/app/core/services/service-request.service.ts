import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceRequest } from '../models/service-request.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServiceRequestService {
  private apiUrl = `${environment.apiUrl}/api/service-request`;

  constructor(private http: HttpClient) {}

  getAllServiceRequests(): Observable<ServiceRequest[]> {
    return this.http.get<ServiceRequest[]>(this.apiUrl);
  }

  getServiceRequestById(id: number): Observable<ServiceRequest> {
    return this.http.get<ServiceRequest>(`${this.apiUrl}/${id}`);
  }

  createServiceRequest(data: ServiceRequest): Observable<ServiceRequest> {
    return this.http.post<ServiceRequest>(this.apiUrl, data);
  }

  updateServiceRequest(id: number, data: ServiceRequest): Observable<ServiceRequest> {
    return this.http.put<ServiceRequest>(`${this.apiUrl}/${id}`, data);
  }

  getServiceRequestsByServicerId(servicerId: number): Observable<ServiceRequest[]> {
    return this.http.get<ServiceRequest[]>(`${this.apiUrl}/servicer/${servicerId}`);
  }

  deleteServiceRequest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
