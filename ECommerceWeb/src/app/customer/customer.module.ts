import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { DashboardCustomerComponent } from './components/dashboard-customer/dashboard-customer.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DemoAngularMaterialModuleTsComponent } from '../DemoAngularMaterialModule';
import { PlaceOrderComponent } from './components/place-order/place-order.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';


@NgModule({
  declarations: [
    CustomerComponent,
    DashboardCustomerComponent,
    PlaceOrderComponent,
    MyOrdersComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoAngularMaterialModuleTsComponent,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule

  ]
})
export class CustomerModule { }
