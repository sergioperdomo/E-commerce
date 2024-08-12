import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-dashborad-admin',
  templateUrl: './dashborad-admin.component.html',
  styleUrls: ['./dashborad-admin.component.scss']
})
export class DashboradAdminComponent {

  products: any[] = [];


  constructor ( private adminService: AdminService ) { }

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.products = [];
    this.adminService.getAllProducts().subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;bases64' + element.byteImg;
        this.products.push(element);
      });
    })
  }

}
