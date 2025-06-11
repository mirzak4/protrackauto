import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { ReportService } from '../../../core/services/report.service';
import { UserInfo } from '../../../core/models/user-info.model';
import { Observable } from 'rxjs';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO } from 'app/core/models/company.model';
import { RoleEnum } from 'app/core/models/enums/role.enums';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
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

  companyId: number | null = null;


  ngOnInit(): void {
    this.authService.getUserInfo$().subscribe(user => {
      this.companyId = user ? user.companyId : null;
    });
  }

  private permissionsMap: Record<string, RoleEnum[]> = {
    companies: [RoleEnum.ADMIN],
    //vehicles: [RoleEnum.ADMIN, RoleEnum.DRIVER, RoleEnum.STATION_MANAGER, RoleEnum.FIELD_TECHNICIAN],
    vehicles: [RoleEnum.ADMIN],
    serviceRequests: [RoleEnum.ADMIN],
    serviceRequestsCompany: [RoleEnum.FIELD_TECHNICIAN],
    trafficFines: [RoleEnum.ADMIN, RoleEnum.DRIVER],
    fuelPrices: [RoleEnum.ADMIN, RoleEnum.STATION_MANAGER],
    usersDropdown: [RoleEnum.ADMIN],
    drivers: [RoleEnum.ADMIN],
    employees: [RoleEnum.ADMIN]
  };

  canAccess(feature: string): boolean {
    if (!this.user) return false;
    const allowedRoles = this.permissionsMap[feature];
    return allowedRoles ? allowedRoles.includes(this.user.role) : false;
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
