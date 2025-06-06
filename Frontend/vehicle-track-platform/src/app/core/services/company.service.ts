import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { CompanyDTO } from '../models/company.model';
import { GasStationFuelPriceReport } from '../models/gas-station-fuel-price-report.model';

@Injectable({ providedIn: 'root' })
export class CompanyService {
  private apiUrl = `${environment.apiUrl}/api/companies`;

  constructor(private http: HttpClient) {}

  getAll(type?: string): Observable<CompanyDTO[]> {
    const params = type ? new HttpParams().set('type', type) : undefined;
    return this.http.get<CompanyDTO[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<CompanyDTO> {
    return this.http.get<CompanyDTO>(`${this.apiUrl}/${id}`);
  }

  create(dto: CompanyDTO): Observable<number> {
    return this.http.post<number>(this.apiUrl, dto);
  }

  update(p0: number, dto: CompanyDTO): Observable<CompanyDTO> {
    return this.http.put<CompanyDTO>(`${this.apiUrl}/${dto.id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getCompanyReports(companyId: number): Observable<GasStationFuelPriceReport[]> {
    return this.http.get<GasStationFuelPriceReport[]>(`${this.apiUrl}/${companyId}/reports`);
  }

  generateWeeklyFuelPriceReport(companyId: number): void {
    this.http.post(`${this.apiUrl}/${companyId}/reports/weekly-fuel-prices`, null, { responseType: 'blob' })
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
