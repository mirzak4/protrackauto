import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from './company.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private baseUrl = 'http://localhost:8080/api/company';


  constructor(private http: HttpClient) {}

  getCompanies(companyType: string): Observable<Company[]> {
    return this.http.get<Company[]>(`${this.baseUrl}?companyType=${companyType}`);
  }

  getCompany(id: number): Observable<Company> {
    return this.http.get<Company>(`${this.baseUrl}/${id}`);
  }

  createCompany(company: Company): Observable<number> {
    return this.http.post<number>(this.baseUrl, company);
  }

  updateCompany(company: Company): Observable<Company> {
    return this.http.put<Company>(`${this.baseUrl}/${company.id}`, company);
  }

  deleteCompany(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
