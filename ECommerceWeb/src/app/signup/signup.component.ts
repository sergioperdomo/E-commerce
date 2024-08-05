import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  signupForm!: FormGroup;

  //  Aquí se almacenara el valor para ocultar la contraseña.
  hidePassword = true;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router


  ){

  }


  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      confirmpassword: [null, [Validators.required]],
    })
  }


  // Este método lo que hace es actualizar el valor cuando se oculta la contraseña.
  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }


  // Método de enviar.
  onSubmit(): void{
    const password = this.signupForm.get('password')?.value;
    const confirmpassword = this.signupForm.get('confirmpassword')?.value;


    // Si la confirmación de la contraseña es diferente a la contraseña arroja el mensaje "La contraseña no coincide"
    if (password !== confirmpassword) {
      this.snackBar.open('Passwords do not match.', 'Close', { duration : 5000, panelClass : 'error-snackbar' });
      return;
    }

    this.authService.register(this.signupForm.value).subscribe(
      (response) => {
        this.snackBar.open('Sign uo successful!', 'Close', { duration : 5000 });

        // Navega al usuario a la página de inicio.
        this.router.navigateByUrl("/login");

      },

      (error) => {
        this.snackBar.open('Sign up failed. Please try again.', 'Close', { duration : 5000, panelClass : 'error-snackbar' });
      }

    )

  }







}
