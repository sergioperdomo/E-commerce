import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

// URL de nuestra aplicación BackEnd

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor( private http: HttpClient) { }
}
