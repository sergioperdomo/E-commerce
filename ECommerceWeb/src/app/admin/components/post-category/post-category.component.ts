import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-post-category',
  templateUrl: './post-category.component.html',
  styleUrls: ['./post-category.component.scss']
})
export class PostCategoryComponent {

  categoryForm!: FormGroup;

  constructor (
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) { }

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]]
    })
  }

  addCagetory(): void {
    if (this.categoryForm.valid){

      // const headers = new HttpHeaders({'Authorization': 'Bearer ${token}'})

      this.adminService.addCategory(this.categoryForm.value).subscribe((res) => {
        if (res.id != null) {
          this.snackBar.open('Category Posted Successfully!', 'Close', {
            duration: 3000
          });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snackBar.open(res.message, 'Close', {
            duration: 3000,
            panelClass: 'error-snackbar'
          });
        }
      })
    } else {
      this.categoryForm.markAllAsTouched();
    }
  }

}
