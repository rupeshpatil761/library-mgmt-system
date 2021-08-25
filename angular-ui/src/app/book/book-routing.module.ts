import { AddBookComponent } from './add-book/add-book.component';
import { ViewBookComponent } from './view-book/view-book.component';
import { BookComponent } from './book.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: BookComponent
  },
  {
    path: 'viewBook/:id',
    component: ViewBookComponent
  },
  {
    path: 'addBook',
    component: AddBookComponent
  },
  {
    path: 'editBook/:id',
    component: AddBookComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
