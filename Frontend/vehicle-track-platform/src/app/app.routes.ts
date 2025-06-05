import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { VehiclesComponent } from './pages/vehicles/vehicles.component';
import { DriverListComponent } from './pages/drivers//driver-list/driver-list.component';
import { DriverFormComponent } from './pages/drivers/driver-form/driver-form.component';
import { ServicesComponent } from './pages/services/services.component';
import { FuelComponent } from './pages/fuel/fuel.component';
import { InsuranceComponent } from './pages/insurance/insurance.component';
import { FinesComponent } from './pages/fines/fines.component';
import { InspectionsComponent } from './pages/inspections/inspections.component';
import { TravelRequestsComponent } from './pages/travel-requests/travel-requests.component';
import { ServiceRequestsComponent } from './pages/service-requests/service-requests.component';
import { AccountComponent } from './pages/account/account.component';
import { LoginComponent } from './pages/login/login.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { CompanyListComponent } from './pages/company/company-list/company-list.component';
import { CompanyCreateComponent } from './pages/company/company-create/company-create.component';
import { CompanyDetailComponent } from './pages/company/company-detail/company-detail.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { VehicleFormComponent } from './pages/vehicles/vehicle-form/vehicle-form.component';
import { VehicleListComponent } from './pages/vehicles/vehicle-list/vehicle-list.component';
import { VehicleDetailsComponent } from './pages/vehicles/vehicle-details/vehicle-details.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'drivers', component: DriverListComponent },
  { path: 'drivers/new', component: DriverFormComponent },
  { path: 'drivers/edit/:id', component: DriverFormComponent },

  { path: 'vehicles', component: VehicleListComponent },
  { path: 'vehicles/new', component: VehicleFormComponent },
  { path: 'vehicles/edit/:id', component: VehicleFormComponent },
  { path: 'vehicles/details', component: VehicleDetailsComponent },

  { path: 'services', component: ServicesComponent },
  { path: 'fuel', component: FuelComponent },
  { path: 'insurance', component: InsuranceComponent },
  { path: 'fines', component: FinesComponent },
  { path: 'inspections', component: InspectionsComponent },
  { path: 'travel-requests', component: TravelRequestsComponent },
  { path: 'service-requests', component: ServiceRequestsComponent },
  { path: 'account', component: AccountComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'company', component: CompanyListComponent },
  { path: 'company/create', component: CompanyCreateComponent },
  { path: 'company/detail/:id', component: CompanyDetailComponent }
];
