import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  menuOpen = false;
  userName = 'John Doe';
  userRole = 'Administrator';
  profileMenuOpen = false;

  constructor(private router: Router) {}
  
  toggleProfileMenu(): void {
    this.profileMenuOpen = !this.profileMenuOpen;
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout(): void {
    console.log('User logged out');
    this.router.navigate(['/login']);
  }
}
