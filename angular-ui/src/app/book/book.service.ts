import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  addNewBook(BookData:any):Observable<Object> {
    return this.http.post(environment.BOOK_SERVICE_URL,BookData);
  }

  updateBook(bookId:any,BookData:any):Observable<Object> {
    return this.http.put(environment.BOOK_SERVICE_URL+bookId,BookData);
  }

  getAllBooks(){
    return this.http.get(environment.BOOK_SERVICE_URL);
  }

  findById(bookId:any){
    return this.http.get(environment.BOOK_SERVICE_URL+bookId);
  }

  deleteBook(id:number) {
    return this.http.delete(environment.BOOK_SERVICE_URL+id);
  }
}
