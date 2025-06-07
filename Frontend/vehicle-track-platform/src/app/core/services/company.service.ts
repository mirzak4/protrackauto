import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyDTO } from '../models/company.model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiUrl = `${environment.apiUrl}/api/company`;

  constructor(private http: HttpClient) {}

  getAllCompanies(): Observable<CompanyDTO[]> {
    return this.http.get<CompanyDTO[]>(this.apiUrl);
  }

  getCompaniesByType(companyType: number): Observable<CompanyDTO[]> {
    const params = new HttpParams().set('companyType', companyType.toString());
    return this.http.get<CompanyDTO[]>(this.apiUrl, { params });
  }

  getCompanyById(id: number): Observable<CompanyDTO> {
    return this.http.get<CompanyDTO>(`${this.apiUrl}/${id}`);
  }

  createCompany(company: CompanyDTO): Observable<number> {
    return this.http.post<number>(this.apiUrl, company);
  }

  updateCompany(id: number, company: CompanyDTO): Observable<CompanyDTO> {
    return this.http.put<CompanyDTO>(`${this.apiUrl}/${id}`, company);
  }

  deleteCompany(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
