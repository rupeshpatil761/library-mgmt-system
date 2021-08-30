import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  userForm: FormGroup ;
  isAddMode?: boolean;
  userId?:number;

  constructor(private formBuilder: FormBuilder,
            private userService: UserService,
            private route: ActivatedRoute,
        private router: Router,) {
    this.userForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(3)]],
      lastName: ['', [Validators.required, Validators.minLength(3)]],
      username: ['', [Validators.required, Validators.maxLength(15), Validators.pattern("^[a-zA-Z]+$")]],
      email: ['', [Validators.required, Validators.email]],
      age: ['', [Validators.required,Validators.pattern('^[0-9]+$')]],
      address: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['id'];
    this.isAddMode = !this.userId;

    if (!this.isAddMode) {
      this.userService.findById(this.userId)
          .subscribe(x => this.userForm.patchValue(x));
  }
  }

  onSubmit() {
    if(this.userForm.invalid){
      return;
    }
    console.log(this.userForm.value);
    if (this.isAddMode) {
      this.createUser();
    } else {
        this.updateUser();
    }
  }

  private createUser() {
    this.userService.addNewUser(this.userForm.value).subscribe(
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
    this.userService.updateUser(this.userId,this.userForm.value).subscribe(
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
      return this.userForm;
  }
}
