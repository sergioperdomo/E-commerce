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




}
