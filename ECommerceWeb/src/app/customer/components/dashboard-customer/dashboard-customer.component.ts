import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/admin/service/admin.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-dashboard-customer',
  templateUrl: './dashboard-customer.component.html',
  styleUrls: ['./dashboard-customer.component.scss']
})
export class DashboardCustomerComponent {

  products: any[] = [];
  searchProductForm!: FormGroup;


  constructor ( private customerService: CustomerService, private fb: FormBuilder, private snackBar: MatSnackBar ) { }

  ngOnInit() {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]]
    })
  }

  getAllProducts() {
    this.products = [];
    this.customerService.getAllProducts().subscribe(res => {
      // console.log('getAllProducts response:', res);
      res.forEach(element => {
        element.processedImg = 'data:image/png;base64,'+ element.byteImg;
        this.products.push(element);
      });
    })
  }

  // Envio del formulario
  submitForm() {
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.customerService.getAllProductsByName(title).subscribe(res => {
      // console.log('getAllProducts response:', res);
      res.forEach(element => {
        element.processedImg = 'data:image/png;base64,'+ element.byteImg;
        this.products.push(element);
      });
    })
  }
}
