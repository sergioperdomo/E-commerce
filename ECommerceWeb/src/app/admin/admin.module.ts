import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboradAdminComponent } from './components/dashborad-admin/dashborad-admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DemoAngularMaterialModuleTsComponent } from '../DemoAngularMaterialModule';
// import { DashboardComponent } from './components/dashboard/dashboard.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboradAdminComponent,
    // DashboardComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DemoAngularMaterialModuleTsComponent
  ]
})
export class AdminModule { }
