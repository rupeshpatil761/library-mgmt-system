import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  addNewUser(userData:any):Observable<Object> {
    return this.http.post(environment.USER_SERVICE_URL,userData);
  }

  updateUser(userId:any,userData:any):Observable<Object> {
    return this.http.put(environment.USER_SERVICE_URL+userId,userData);
  }

  getAllUsers(){
    return this.http.get(environment.USER_SERVICE_URL);
  }

  findById(userId:any){
    return this.http.get(environment.USER_SERVICE_URL+userId);
  }

  deleteUser(id:number) {
    return this.http.delete(environment.USER_SERVICE_URL+id);
  }
}
