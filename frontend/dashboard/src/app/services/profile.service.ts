import { Injectable } from '@angular/core';
import { AuthService } from '../core/services/auth.service';
import { Profile } from '../model/profile';
import { HttpRestService } from './http.rest.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  profileEndpoint:string

  constructor(private _authService: AuthService,private httpRestService: HttpRestService) {
    this.profileEndpoint = `/profile/${_authService.getLoggedInUsername()}`;
  }

  getUserProfileData() {
    return this.httpRestService.getService(this.profileEndpoint);
  }

  updateProfileData(profile: Profile) {
    return this.httpRestService.putService(this.profileEndpoint, profile);
  }
}
