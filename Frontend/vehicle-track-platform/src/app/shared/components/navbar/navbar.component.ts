import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { ReportService } from '../../../core/services/report.service';
import { UserInfo } from '../../../core/models/user-info.model';
import { Observable } from 'rxjs';

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

  constructor(
    private router: Router,
    private authService: AuthService,
    private reportService: ReportService
  ) {
    this.userInfo$ = this.authService.getUserInfo$();
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
    this.reportService.downloadWeeklyFuelPriceReport(156);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/logout']);
  }
}
