import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent {

  orders: any;

  constructor(
    private adminService: AdminService, private snackBar: MatSnackBar
  ){ }

  ngOnInit(){
    this.getPlacedOrders();
  }

  // Verificamos que este llegando el token y si esta llegando correctamente. Se descarta que el error sea producto del token.
  getPlacedOrders(){
    console.log('Token: ', UserStorageService.getToken()); // En la consola del naevagor se puede observar que si llega el token.
    this.adminService.getPlacedOrders().subscribe(res => {
      this.orders = res;
      console.log('Orders received:', res);
    })
  }

}
