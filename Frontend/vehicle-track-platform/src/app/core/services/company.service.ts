import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyDTO } from '../models/company.model';
import { GasStationFuelPriceReport } from '../models/gas-station-fuel-price-report.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiUrl = `${environment.apiUrl}/api/companies`;

  constructor(private http: HttpClient) {}

  getAll(companyType?: string): Observable<CompanyDTO[]> {
    const params = companyType ? new HttpParams().set('companyType', companyType) : undefined;
    return this.http.get<CompanyDTO[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<CompanyDTO> {
    return this.http.get<CompanyDTO>(`${this.apiUrl}/${id}`);
  }

  createCompany(dto: CompanyDTO): Observable<CompanyDTO> {
    return this.http.post<CompanyDTO>(this.apiUrl, dto);
  }

  updateCompany(id: number, dto: CompanyDTO): Observable<CompanyDTO> {
    return this.http.put<CompanyDTO>(`${this.apiUrl}/${id}`, dto);
  }

  deleteCompany(id: number): Observable<void> {
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
