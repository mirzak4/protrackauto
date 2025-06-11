import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

import { routes } from './app.routes';
//import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { authInterceptorFn } from './core/interceptors/auth.interceptor';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { provideAnimations } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { importProvidersFrom } from '@angular/core';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    // provideClientHydration(withEventReplay()),
    provideHttpClient(withInterceptors([authInterceptorFn])), 
    provideCharts(withDefaultRegisterables()),
    provideAnimations(),
    importProvidersFrom(FormsModule),
  ]
};
