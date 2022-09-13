import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarouselConfig } from 'ngx-bootstrap/carousel';
import { UserService } from 'src/app/core/services/user.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [
    { provide: CarouselConfig, useValue: { interval: 3000, noPause: true, showIndicators: false } }
  ]
})
export class RegisterComponent implements OnInit {

  username:string='';
  password:string='';

  constructor(private _userService:UserService, private _notificationService:NotificationService,
    private _router:Router) { }

  ngOnInit(): void {
  }

  registerUser() {
    this._userService.registerUser({"username":this.username,"password":this.password}).subscribe((response)=>{
      console.log("user registered succesfully");
      this._notificationService.successMessage("user created successfully.!");
      this._router.navigate(['/login']);
    })
  }

}
