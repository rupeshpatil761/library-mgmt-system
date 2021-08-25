import { BookService } from './../book.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.scss']
})
export class ViewBookComponent implements OnInit {

  bookId?:number;
  bookData?: any;
  constructor(
    private formBuilder: FormBuilder,
    private userService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['id'];
    this.userService.findById(this.bookId)
          .subscribe(x => this.bookData=x);
  }

}
