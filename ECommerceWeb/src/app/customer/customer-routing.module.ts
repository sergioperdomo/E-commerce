import { ViewOrderedProductsComponent } from './components/view-ordered-products/view-ordered-products.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerComponent } from './customer.component';
import { DashboardCustomerComponent } from './components/dashboard-customer/dashboard-customer.component';
import { CartComponent } from '../admin/components/cart/cart.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';
import { ReviewOrderedProductComponent } from './components/review-ordered-product/review-ordered-product.component';
import { ViewProductDetailComponent } from './components/view-product-detail/view-product-detail.component';
import { ViewWishListComponent } from './components/view-wish-list/view-wish-list.component';


const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardCustomerComponent },
  { path: 'cart', component: CartComponent },
  { path: 'my_orders', component: MyOrdersComponent},
  { path: 'ordered_products/:orderId', component: ViewOrderedProductsComponent},
  { path: 'review/:productId', component: ReviewOrderedProductComponent},
  { path: 'product/:productId', component: ViewProductDetailComponent},
  { path: 'wishlist', component: ViewWishListComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
