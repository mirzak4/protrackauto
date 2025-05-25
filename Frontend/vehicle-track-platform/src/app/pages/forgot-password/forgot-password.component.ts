import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';

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
  emailSent = false;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.forgotForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.forgotForm.invalid) {
      return;
    }

    this.isLoading = true;
    
    // Simulate API call to send reset code
    setTimeout(() => {
      this.isLoading = false;
      this.emailSent = true;
      // Navigate to reset page after 2 seconds
      setTimeout(() => {
        this.router.navigate(['/reset-password'], {
          state: { email: this.forgotForm.value.email }
        });
      }, 2000);
    }, 1500);
  }

  get email() {
    return this.forgotForm.get('email');
  }
}