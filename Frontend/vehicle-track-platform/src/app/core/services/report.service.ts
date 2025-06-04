import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private apiUrl = `${environment.apiUrl}/api/reports`;

  constructor(private http: HttpClient) {}

  downloadWeeklyFuelPriceReport(companyId: number): void {
    this.http.get(`${this.apiUrl}/company/${companyId}/weekly-fuel-prices`, { responseType: 'blob' })
      .subscribe(blob => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'weekly-fuel-prices.pdf';
        link.click();
        window.URL.revokeObjectURL(url);
      });
  }
} 