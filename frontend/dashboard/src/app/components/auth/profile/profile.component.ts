import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/model/profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profile?:Profile

  constructor() { }

  ngOnInit(): void {
  }

}
