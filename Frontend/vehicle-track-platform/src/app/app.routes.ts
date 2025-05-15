import { Routes } from '@angular/router';
import { CompaniesListComponent } from './pages/companies/companies-list.component';
import { CompanyFormComponent } from './pages/companies/company-form.component';

export const routes: Routes = [
  { path: 'companies', component: CompaniesListComponent },
  { path: 'companies/new', component: CompanyFormComponent },
  { path: 'companies/:id', component: CompanyFormComponent },
];
