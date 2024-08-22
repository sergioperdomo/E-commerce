import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, throwError } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/" ;

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor( private http: HttpClient ) { }

  getAllProducts(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/customer/products',{
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProductsByName(name: any): Observable<any>{
    return this.http.get(BASIC_URL + `api/customer/search/${name}`,{
      headers: this.createAuthorizationHeader(),
    })
  }

  addToCart(productId: any): Observable<any> {
    const cartDto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    };

    return this.http.post(BASIC_URL + 'api/customer/cart', cartDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      catchError(error => {
        // Maneja el error aquí
        console.error("Error adding to cart:", error);
        // Puedes retornar un Observable con un valor apropiado (ej: null)
        return of(null);
      })
    );
  }

  increaseProductQuantity(productId: any): Observable<any> {
    const cartDto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    }
    return this.http.post(BASIC_URL + `api/customer/addition`, cartDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getCartByUserId(): Observable<any> {
    const userId = UserStorageService.getUserId()

    return this.http.get(BASIC_URL + `api/customer/cart/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      catchError(error => {
        // Maneja el error aquí
        console.error("Error fetching  to cart:", error);
        // Puedes retornar un Observable con un valor apropiado (ej: null)
        return of(null);
      })
    );
  }

  applyCoupon(code: any): Observable<any> {
    const userId = UserStorageService.getUserId()

    return this.http.get(BASIC_URL + `api/customer/coupon/${userId}/${code}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      catchError(error => {
        // Maneja el error aquí
        console.error("Coupon applied successfully:", error);
        // Puedes retornar un Observable con un valor apropiado (ej: null)
        return throwError(() => new Error('Error applying coupon'))
      })
    );
  }


  //  Método para autorizar
  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + UserStorageService.getToken()
    )
  }

}
