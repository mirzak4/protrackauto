import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService, ToastNotification } from '../../../core/services/notification.service';
import { Subscription } from 'rxjs';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'toast',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="toast-container">
      @for (toast of toasts; track toast.id) {
        <div class="toast-item" 
             [class]="toast.type" 
             [@toastAnimation]="'visible'"
             (@toastAnimation.done)="onAnimationDone(toast.id)">
          <div class="toast-content">
            <i [class]="getIcon(toast.type)"></i>
            <span>{{ toast.message }}</span>
          </div>
          <button class="toast-close" (click)="removeToast(toast.id)">
            <i class="fas fa-times"></i>
          </button>
        </div>
      }
    </div>
  `,
  styles: [`
    .toast-container {
      position: fixed;
      top: 20px;
      right: 20px;
      z-index: 1000;
      display: flex;
      flex-direction: column;
      gap: 10px;
      max-width: 350px;
    }

    .toast-item {
      padding: 16px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      display: flex;
      align-items: center;
      justify-content: space-between;
      min-width: 300px;
      max-width: 100%;
    }

    .toast-content {
      display: flex;
      align-items: center;
      gap: 12px;
      flex: 1;
    }

    .toast-content i {
      font-size: 20px;
    }

    .toast-close {
      background: none;
      border: none;
      color: inherit;
      opacity: 0.7;
      cursor: pointer;
      padding: 0;
      margin-left: 12px;
    }

    .toast-close:hover {
      opacity: 1;
    }

    .success {
      background-color: #dcfce7;
      color: #166534;
      border: 1px solid #bbf7d0;
    }

    .warning {
      background-color: #fef9c3;
      color: #854d0e;
      border: 1px solid #fef08a;
    }

    .error {
      background-color: #fee2e2;
      color: #b91c1c;
      border: 1px solid #fecaca;
    }
  `],
  animations: [
    trigger('toastAnimation', [
      state('void', style({
        transform: 'translateX(100%)',
        opacity: 0
      })),
      state('visible', style({
        transform: 'translateX(0)',
        opacity: 1
      })),
      transition('void => visible', animate('200ms ease-out')),
      transition('visible => void', animate('150ms ease-in'))
    ])
  ]
})
export class ToastComponent implements OnInit, OnDestroy {
  toasts: ToastNotification[] = [];
  private subscription: Subscription | null = null;

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.subscription = this.notificationService.getNotifications().subscribe(toast => {
      this.toasts.push(toast);
      // Auto remove after 5 seconds
      setTimeout(() => this.removeToast(toast.id), 5000);
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  removeToast(id: number) {
    this.toasts = this.toasts.filter(t => t.id !== id);
  }

  onAnimationDone(id: number) {
    // This is called when the animation is complete
    // You can use this to clean up if needed
  }

  getIcon(type: 'success' | 'warning' | 'error'): string {
    switch (type) {
      case 'success':
        return 'fas fa-check-circle';
      case 'warning':
        return 'fas fa-exclamation-triangle';
      case 'error':
        return 'fas fa-exclamation-circle';
    }
  }
} 