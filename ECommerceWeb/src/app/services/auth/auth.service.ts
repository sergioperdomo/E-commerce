import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/"; // URL de nuestra aplicación BackEnd.

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private http: HttpClient ) { }

  register(sigunupRequest: any): Observable<any>{
    return this.http.post(BASIC_URL + "sign-up", sigunupRequest);
  }


}
