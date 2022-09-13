import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Profile } from 'src/app/model/profile';
import { NotificationService } from 'src/app/services/notification.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  profileForm:any;
  @Input() profile?:Profile;

  constructor(private _formBuilder: FormBuilder, private profileService: ProfileService, private _notifService: NotificationService, private _router: Router) { }

  ngOnInit(): void {
    this.profileForm = this._formBuilder.group({
      email: [null, Validators.email],
      name: [null, {
        validators: [
          Validators.required, Validators.minLength(1)
          ]
        }
      ],
    });
  }

  initializeData() {
    if(this.profile) {
      this.profileForm.patchValue(
        {
          email: this.profile.email,
          name: this.profile.name
        }
      );
    }
  }

  onSave() {
    if(this.profile) {
      let data = this.profileForm.value;
      let profile = this.profile;
      profile.email = data.email;
      profile.name = data.name;
      this.profileService.updateProfileData(profile).subscribe(
        (resp) => {
          this._notifService.successMessage("Profile updated successfully");
          this.profile = profile;
        },
        (err) => {
          this._notifService.showError("Failed to update profile. Please try later.");
          this.initializeData();
        }
      );
    }
  }

}
