import { BookService } from './../book.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {

  bookForm: FormGroup ;
  isAddMode?: boolean;
  bookId?:number;

  constructor(private formBuilder: FormBuilder,
            private bookService: BookService,
            private route: ActivatedRoute,
        private router: Router,) {
    this.bookForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      price: ['', [Validators.required,Validators.pattern(/^\d+\.\d{2}$/)]],
      author: ['', [Validators.required, Validators.minLength(3)]],
      category: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['id'];
    this.isAddMode = !this.bookId;

    if (!this.isAddMode) {
      this.bookService.findById(this.bookId)
          .subscribe(x => this.bookForm.patchValue(x));
  }
  }

  onSubmit() {
    if(this.bookForm.invalid){
      return;
    }
    console.log(this.bookForm.value);
    if (this.isAddMode) {
      this.createUser();
    } else {
        this.updateUser();
    }
  }

  private createUser() {
    this.bookService.addNewBook(this.bookForm.value).subscribe(
      response =>{
        console.log(response)
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      error=> {
        alert(error.error.message)
        console.log(error.error.message)
      });
  }

  private updateUser() {
    this.bookService.updateBook(this.bookId,this.bookForm.value).subscribe(
      response =>{
        console.log(response)
        this.router.navigate(['../../'], { relativeTo: this.route });
      },
      error=> {
        alert(error.error.message)
        console.log(error.error.message)
      });
  }
  get formControl(){
      return this.bookForm;
  }

}
