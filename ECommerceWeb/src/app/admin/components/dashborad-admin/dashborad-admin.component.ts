import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-dashborad-admin',
  templateUrl: './dashborad-admin.component.html',
  styleUrls: ['./dashborad-admin.component.scss']
})
export class DashboradAdminComponent {

  products: any[] = [];
  searchProductForm!: FormGroup;


  constructor ( private adminService: AdminService, private fb: FormBuilder, private snackBar: MatSnackBar ) { }

  ngOnInit() {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]]
    })
  }

  getAllProducts() {
    this.products = [];
    this.adminService.getAllProducts().subscribe(res => {
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
    this.adminService.getAllProductsByName(title).subscribe(res => {
      // console.log('getAllProducts response:', res);
      res.forEach(element => {
        element.processedImg = 'data:image/png;base64,'+ element.byteImg;
        this.products.push(element);
      });
    })
  }

  // deleteProduct(productId: any) {
  //   this.adminService.deleteProduct(productId).subscribe(res => {
  //     if(res.body == null) {
  //       this.snackBar.open('Product Deleted Succesfully!', 'Close', {
  //         duration: 3000
  //       });
  //       this.getAllProducts();
  //     } else {
  //       this.snackBar.open(res.message, 'Close', {
  //         duration: 3000,
  //         panelClass: 'error-snackbar'
  //       });
  //     }
  //   })
  // }

  deleteProduct(productId: any) {
    this.adminService.deleteProduct(productId)
        .subscribe(
            res => {
                this.snackBar.open(res?.message || 'Product successfully deleted!', 'Close', {
                    duration: 3000
                });
                this.getAllProducts();
            },
            error => {
                console.error('Error deleting the product:', error);
                this.snackBar.open('Error deleting the product. Please try again.', 'Close', {
                    duration: 3000,
                    panelClass: 'error-snackbar'
                });
            }
        );
}

}
