import { Injectable } from '@angular/core';

const TOKEN = 'ecom-token';
const USER = 'ecom.user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }



  public saveToken( token: string ): void {
    // Eliminamos el token que se encuentra almacenado localmente.

    window.localStorage.removeItem(TOKEN);

    // Almacenamos un nuevo token.

    window.localStorage.setItem(TOKEN, token);
  }


  public saveUser( user ): void {
    // Eliminamos el usuario que se encuentra almacenado localmente.

    window.localStorage.removeItem(USER);

    // Almacenamos un nuevo usuario.

    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  // Este método obtendra el token de nuestro almacenamiento en local y lo retornara en una cadena.
  static getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): string {
    const user = this.getUser();
    if(user == null ) {
      return '';
    }
    return user.userId;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if(user == null ) {
      return '';
    }
    return user.role;
  }

  // Método que nos permite verificar si el usuario o persona que inicio sesión es administrdaor o no.
  static isAdminLoggedIn(): boolean {
    if (this.getToken === null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }

   // Método que nos permite verificar si el usuario o persona que inicio sesión es cliente o no.
  static isCustomerLoggedIn(): boolean {
    if (this.getToken === null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'CUSTOMER';
  }

  // Método de cerrar sesión.
  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
