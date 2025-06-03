import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface ToastNotification {
  message: string;
  type: 'success' | 'warning' | 'error';
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notifications$ = new Subject<ToastNotification>();
  private counter = 0;

  getNotifications() {
    return this.notifications$.asObservable();
  }

  showSuccess(message: string) {
    this.show(message, 'success');
  }

  showWarning(message: string) {
    this.show(message, 'warning');
  }

  showError(message: string) {
    this.show(message, 'error');
  }

  private show(message: string, type: 'success' | 'warning' | 'error') {
    this.notifications$.next({
      message,
      type,
      id: this.counter++
    });
  }
} 