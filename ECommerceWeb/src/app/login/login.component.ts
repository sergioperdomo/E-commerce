import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;
  hidePassword = true;

  constructor (
    private FormBuilder: FormBuilder,
    private authService: AuthService,
    private snackbar: MatSnackBar,
    private router: Router,

  ){  }

  ngOnInit() : void {
    this.loginForm = this.FormBuilder.group({
      email : [null, [Validators.required]],
      password : [null, [Validators.required]]
    })
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void{

    const username = this.loginForm.get('email')!.value;
    const password = this.loginForm.get('password')!.value;

    this.authService.login(username, password).subscribe(
      (res) => {
        this.snackbar.open('Login Success', 'Ok', { duration : 5000 })
      },
      (error) => {
        this.snackbar.open('Bad Credentials', 'ERROR', { duration : 5000 })
      }
    );

  }




}
