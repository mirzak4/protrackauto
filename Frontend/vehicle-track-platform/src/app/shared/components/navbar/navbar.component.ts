import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { ReportService } from '../../../core/services/report.service';
import { UserInfo } from '../../../core/models/user-info.model';
import { Observable } from 'rxjs';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO } from 'app/core/models/company.model';
import { RoleEnum } from 'app/core/enums/role.enum';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  menuOpen = false;
  profileMenuOpen = false;
  userInfo$: Observable<UserInfo | null>;
  user: UserInfo | null = null;

  constructor(
    private router: Router,
    private authService: AuthService,
    private companyService: CompanyService
  ) {
    this.userInfo$ = this.authService.getUserInfo$();
    this.userInfo$.subscribe(user => this.user = user);
  }

  isAdmin(): boolean {
    return this.user?.role === RoleEnum.ADMIN;
  }

  isDriver(): boolean {
    return this.user?.role === RoleEnum.DRIVER;
  }

  isStationManager(): boolean {
    return this.user?.role === RoleEnum.STATION_MANAGER;
  }

  isFieldTechnician(): boolean {
    return this.user?.role === RoleEnum.FIELD_TECHNICIAN;
  }

  isClaimsAdjuster(): boolean {
    return this.user?.role === RoleEnum.CLAIMS_ADJUSTER;
  }

  canSeeCompanies(): boolean {
    return this.isAdmin();  // Only admin sees companies
  }

  canSeeVehicles(): boolean {
    return this.isAdmin() || this.isDriver() || this.isStationManager() || this.isFieldTechnician();
  }

  canSeeServiceRequests(): boolean {
    return this.isAdmin() || this.isFieldTechnician();
  }

  canSeeTrafficFines(): boolean {
    return this.isAdmin() || this.isDriver();
  }

  canSeeFuelPrices(): boolean {
    return this.isAdmin() || this.isStationManager();
  }

  canSeeUsersDropdown(): boolean {
    return this.isAdmin();
  }

  canSeeDrivers(): boolean {
    return this.isAdmin();
  }

  canSeeEmployees(): boolean {
    return this.isAdmin();
  }

  toggleProfileMenu(): void {
    this.profileMenuOpen = !this.profileMenuOpen;
  }

  onProfileBlur(event: FocusEvent): void {
    const target = event.relatedTarget as HTMLElement;
    if (!target?.closest('.user-profile-dropdown')) {
      this.profileMenuOpen = false;
    }
  }

  downloadReport(): void {
    this.companyService.generateWeeklyFuelPriceReport(156);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/logout']);
  }
}
