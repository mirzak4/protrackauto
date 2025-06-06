import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private reportsUrl = `${environment.apiUrl}/api/reports`;

  constructor(private http: HttpClient) {}

  getReportDocument(reportId: number): Observable<Blob> {
    return this.http.get(`${this.reportsUrl}/${reportId}/document`, { responseType: 'blob' });
  }
} 