import { ViewUserComponent } from './view-user/view-user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';

const routes: Routes = [
  {
    path: '',
    component: UserComponent
  },
  {
    path: 'viewUser/:id',
    component: ViewUserComponent
  },
  {
    path: 'addUser',
    component: AddUserComponent
  },
  {
    path: 'editUser/:id',
    component: AddUserComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
