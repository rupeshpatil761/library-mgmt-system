import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users:any;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(response => {
      console.log(response);
      this.users = response;
    });
  }

  updateUser(userId:any){
    alert('updateBook: '+userId);
  }

  deleteUser(userId:number){
    this.userService.deleteUser(userId).subscribe(response=>{
      console.log(response);
      this.users=this.users.filter((u:any)=>u.userId!=userId);
    });
  }

}
