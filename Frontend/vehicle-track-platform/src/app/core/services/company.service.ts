import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyDTO } from '../models/company.model';

@Injectable({ providedIn: 'root' })
export class CompanyService {
  private apiUrl = '/api/company';

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
}
