import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccountsOverviewComponent } from './accounts-overview/accounts-overview.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';

const routes: Routes = [
  { path: "login", component: LoginComponent},
  { path: "accounts/overview", component: AccountsOverviewComponent},
  { path: "accounts/add", component: AddAccountComponent},
  { path: "customer/add", component: AddCustomerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
