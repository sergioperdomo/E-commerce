import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerComponent } from './customer.component';
import { DashboardCustomerComponent } from './components/dashboard-customer/dashboard-customer.component';
import { CartComponent } from '../admin/components/cart/cart.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';


const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardCustomerComponent },
  { path: 'cart', component: CartComponent },
  { path: 'my_orders', component: MyOrdersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
