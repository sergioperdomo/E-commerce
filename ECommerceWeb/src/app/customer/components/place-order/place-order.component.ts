import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.scss']
})
export class PlaceOrderComponent {

  orderForm!:FormGroup;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
     private snackBar: MatSnackBar,
     private router: Router,
     private dialog: MatDialog
  ){}

  ngOnInit(){
    // Mencionamos los controladores
    this.orderForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDescription: [null],
    })
  }

  placeOrder(){
    this.customerService.placeOrder(this.orderForm.value).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Order placed successfully", "Close", { duration:3000 });
        this.router.navigateByUrl("/customer/my-orders");
        this.closeForm();
      } else {
        this.snackBar.open("Something went wrong", "Close", { duration: 3000 } );
      }
    })
  }

  closeForm(){
    this.dialog.closeAll();
  }
}
