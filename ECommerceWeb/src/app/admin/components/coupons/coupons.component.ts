import { AdminComponent } from './../../admin.component';
import { AdminService } from './../../service/admin.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss']
})
export class CouponsComponent {

  coupons: any;

  constructor( private adminService: AdminService ){}

  ngOnInit(){
    this.getCoupons();
  }

  // Llamando la API get all coupon
  getCoupons(){
    this.adminService.getCoupons().subscribe(res => {
      this.coupons = res;
    })
  }

}
