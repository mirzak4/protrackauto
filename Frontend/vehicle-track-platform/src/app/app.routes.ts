import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DriverListComponent } from './pages/drivers//driver-list/driver-list.component';
import { DriverFormComponent } from './pages/drivers/driver-form/driver-form.component';
import { ServicesComponent } from './pages/services/services.component';
import { FuelComponent } from './pages/fuel/fuel.component';
import { InsuranceComponent } from './pages/insurance/insurance.component';
import { FinesComponent } from './pages/fines/fines.component';
import { InspectionsComponent } from './pages/inspections/inspections.component';
import { TravelRequestsComponent } from './pages/travel-requests/travel-requests.component';
import { AccountComponent } from './pages/account/account.component';
import { LoginComponent } from './pages/login/login.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { CompanyListComponent } from './pages/company/company-list/company-list.component';
import { CompanyFormComponent } from './pages/company/company-form/company-form.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { VehicleFormComponent } from './pages/vehicles/vehicle-form/vehicle-form.component';
import { VehicleListComponent } from './pages/vehicles/vehicle-list/vehicle-list.component';
import { EmployeeFormComponent } from './pages/employee/employee-form/employee-form.component';
import { EmployeeListComponent } from './pages/employee/employee-list/employee-list.component';
import { ServiceRequestFormComponent } from './pages/service-request/service-request-form/service-request-form.component';
import { ServiceRequestListComponent } from './pages/service-request/service-request-list/service-request-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'drivers', component: DriverListComponent },
  { path: 'drivers/new', component: DriverFormComponent },
  { path: 'drivers/edit/:id', component: DriverFormComponent },

  { path: 'employees', component: EmployeeListComponent },
  { path: 'employees/new', component: EmployeeFormComponent },
  { path: 'employees/edit/:id', component: EmployeeFormComponent },

  { path: 'companies', component: CompanyListComponent },
  { path: 'companies/new', component: CompanyFormComponent },
  { path: 'companies/edit/:id', component: CompanyFormComponent },

  { path: 'vehicles', component: VehicleListComponent },
  { path: 'vehicles/new', component: VehicleFormComponent },
  { path: 'vehicles/edit/:id', component: VehicleFormComponent },

  { path: 'service-requests', component: ServiceRequestListComponent },
  { path: 'service-requests/new', component: ServiceRequestFormComponent },
  { path: 'service-requests/edit/:id', component: ServiceRequestFormComponent },
  
  { path: 'services', component: ServicesComponent },
  { path: 'fuel', component: FuelComponent },
  { path: 'insurance', component: InsuranceComponent },
  { path: 'fines', component: FinesComponent },
  { path: 'inspections', component: InspectionsComponent },
  { path: 'travel-requests', component: TravelRequestsComponent },
  { path: 'account', component: AccountComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent }
];
