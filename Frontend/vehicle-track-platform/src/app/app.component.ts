import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { ToastComponent } from './shared/components/toast/toast.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavbarComponent,
    ToastComponent
  ],
  template: `
    <app-navbar></app-navbar>
    <app-toast></app-toast>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
  title = 'vehicle-track-platform';
}
