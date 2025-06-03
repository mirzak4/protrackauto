import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { NotificationService } from '../../core/services/notification.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgIf
  ],
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  resetForm: FormGroup;
  isLoading = false;
  resetSuccess = false;
  email: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private notificationService: NotificationService
  ) {
    this.resetForm = this.fb.group({
      email: [{value: '', disabled: true}, [Validators.required, Validators.email]],
      code: ['', [Validators.required, Validators.minLength(5)]],
      newPassword: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const email = params['email'];
      if (email) {
        this.email = email;
        this.resetForm.patchValue({ email });
      } else {
        this.notificationService.showWarning('No email provided. Redirecting to forgot password page...');
        this.router.navigate(['/forgot-password']);
      }
    });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('newPassword')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onSubmit(): void {
    this.resetForm.markAllAsTouched();

    if (this.resetForm.invalid) {
      return;
    }

    this.isLoading = true;
    
    const { code, newPassword } = this.resetForm.value;

    this.authService.resetPassword(this.email, code, newPassword).subscribe({
      next: () => {
        this.isLoading = false;
        this.resetSuccess = true;
        // Navigate to login after 2 seconds
        setTimeout(() => {
          this.notificationService.showSuccess('Password reset successfully.');
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.isLoading = false;
        const errorMessage = error.error?.message || 'Failed to reset password. Please try again.';
        this.notificationService.showError(errorMessage);
      }
    });
  }

  get emailControl() {
    return this.resetForm.get('email');
  }

  get code() {
    return this.resetForm.get('code');
  }

  get newPassword() {
    return this.resetForm.get('newPassword');
  }

  get confirmPassword() {
    return this.resetForm.get('confirmPassword');
  }
}