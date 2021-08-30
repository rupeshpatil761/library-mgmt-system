import { BookService } from './book.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  books:any;

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(response => {
      console.log(response);
      this.books = response;
    });
  }

  updateBook(bookId:any){
    alert('updateBook: '+bookId);
  }

  deleteBook(bookId:number){
    this.bookService.deleteBook(bookId).subscribe(response=>{
      console.log(response);
      this.books=this.books.filter((u:any)=>u.bookId!=bookId);
    });
  }

}
