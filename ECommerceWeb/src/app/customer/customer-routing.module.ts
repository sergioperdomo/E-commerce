import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerComponent } from './customer.component';
import { DashboardCustomerComponent } from './components/dashboard-customer/dashboard-customer.component';
import { CartComponent } from '../admin/components/cart/cart.component';


const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardCustomerComponent },
  { path: 'cart', component: CartComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
