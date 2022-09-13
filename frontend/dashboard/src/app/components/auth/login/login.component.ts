import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CarouselConfig } from 'ngx-bootstrap/carousel';
import { ApplicationConstants } from 'src/app/constants/application.constants';
import { AuthService } from 'src/app/core/services/auth.service';
import { NotificationService } from 'src/app/services/notification.service';
import { StoreProvider } from 'src/app/shared/utility/store';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    { provide: CarouselConfig, useValue: { interval: 3000, noPause: true, showIndicators: false } }
  ]
})
export class LoginComponent implements OnInit {
  form:any

  constructor(private _authService: AuthService, private _httpClient: HttpClient, private _router: Router,
    private _store: StoreProvider, private _formBuilder: FormBuilder, private _notifService: NotificationService) { }

  ngOnInit(): void {
    this.form = this._formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    })
  }

  submitLoginForm() {
    this.form.get('username').markAsTouched();
    this.form.get('password').markAsTouched();
    if(this.form.valid) {
        this._authService.login(this.form.value.username, this.form.value.password).subscribe(
          (response: any) => {
            this._store.setStoreItem(ApplicationConstants.ACCESS_TOKEN_KEY, response.accessToken);
            this._store.setStoreItem(ApplicationConstants.ACTIVE_USER_USERNAME, this.form.value.username);
            this._router.navigate(['/']);
          },
          (error: any) => {
            if(error instanceof HttpErrorResponse) {
              if ([401, 403].indexOf(error.status) !== -1) {
                this._notifService.showError("Username/Password incorrect. Failed to login.");
                this.form.get('password').setValue("");
                this.form.get('password').markAsUntouched();
              }
              else {
                this._notifService.showError("Failed to login. Please try after some time");
              }
            } else {
              this._notifService.showError("Client Error: Failed to connect to server. Please try after some time");
            }
          }
        );
    }
  }

}
