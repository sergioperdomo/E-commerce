import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboradAdminComponent } from './components/dashborad-admin/dashborad-admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DemoAngularMaterialModuleTsComponent } from '../DemoAngularMaterialModule';
import { PostCategoryComponent } from './components/post-category/post-category.component';
import { PostProductComponent } from './components/post-product/post-product.component';
import { CartComponent } from './components/cart/cart.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboradAdminComponent,
    PostCategoryComponent,
    PostProductComponent,
    CartComponent,
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
