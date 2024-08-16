import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-dashborad-admin',
  templateUrl: './dashborad-admin.component.html',
  styleUrls: ['./dashborad-admin.component.scss']
})
export class DashboradAdminComponent {

  products: any[] = [];
  searchProductForm!: FormGroup;


  constructor ( private adminService: AdminService, private fb: FormBuilder ) { }

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

}
