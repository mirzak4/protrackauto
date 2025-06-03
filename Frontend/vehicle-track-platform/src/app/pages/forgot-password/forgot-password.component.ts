import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { NotificationService } from '../../core/services/notification.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgIf
  ],
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  forgotForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private notificationService: NotificationService
  ) {
    this.forgotForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    this.forgotForm.markAllAsTouched();

    if (this.forgotForm.invalid) {
      return;
    }

    this.isLoading = true;
    
    const { email } = this.forgotForm.value;

    this.authService.sendResetPasswordLink(email).subscribe({
      next: () => {
        this.isLoading = false;
        this.notificationService.showSuccess('Reset instructions sent to your email.');
        // Navigate to reset page after 2 seconds
        setTimeout(() => {
          this.router.navigate(['/reset-password'], {
            queryParams: { email }
          });
        }, 2000);
      },
      error: (error) => {
        this.isLoading = false;
        const errorMessage = error.error?.message || 'Failed to send reset link. Please try again.';
        this.notificationService.showError(errorMessage);
      }
    });
  }

  get email() {
    return this.forgotForm.get('email');
  }
}