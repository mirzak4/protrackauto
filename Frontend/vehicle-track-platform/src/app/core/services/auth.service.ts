import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginResponse } from '../models/login-response.model';
import { UserInfo } from '../models/user-info.model';
import { environment } from '../../../environments/environment';
import { RoleEnum } from '../enums/role.enum';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly tokenKey = 'access_token';
  private readonly userKey = 'user_info';
  private userInfoSubject = new BehaviorSubject<UserInfo | null>(this.loadUserInfo());

  constructor(private http: HttpClient) {}

  private loadUserInfo(): UserInfo | null {
    if (typeof window !== 'undefined') {
      const userInfo = localStorage.getItem(this.userKey);
      return userInfo ? JSON.parse(userInfo) : null;
    }
    return null;
  }

  getUserInfo$(): Observable<UserInfo | null> {
    return this.userInfoSubject.asObservable();
  }

  getToken(): string | null {
    if (typeof window !== 'undefined') {
      return localStorage.getItem(this.tokenKey);
    }
    return null;
  }

  private setToken(token: string): void {
    if (typeof window !== 'undefined') {
      localStorage.setItem(this.tokenKey, token);
    }
  }

  private clearStorage(): void {
    if (typeof window !== 'undefined') {
      localStorage.removeItem(this.tokenKey);
      localStorage.removeItem(this.userKey);
    }
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.apiUrl}/api/user/login`, { email, password })
      .pipe(
        tap(response => {
          this.setToken(response.token);
          const userInfo: UserInfo = {
            email: response.email,
            firstName: response.firstName,
            lastName: response.lastName,
            companyId: response.companyId,
            role: response.role as RoleEnum
          };
          localStorage.setItem(this.userKey, JSON.stringify(userInfo));
          this.userInfoSubject.next(userInfo);
        })
      );
  }

  logout(): void {
    this.clearStorage();
    this.userInfoSubject.next(null);
  }

  sendResetPasswordLink(email: string): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/api/user/password-reset/link`, null, {
      params: { email }
    });
  }

  resetPassword(email: string, code: string, newPassword: string): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/api/user/password-reset`, {
      email,
      verificationCode: code,
      newPassword
    });
  }

  getCompanyId(): number | null {
    const user = this.userInfoSubject.getValue();
    return user ? user.companyId : null;
  }

}
