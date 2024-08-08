import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboradAdminComponent } from './components/dashborad-admin/dashborad-admin.component';
import { PostCategoryComponent } from './components/post-category/post-category.component';



const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'dashboard', component: DashboradAdminComponent },
  { path: 'category', component: PostCategoryComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
