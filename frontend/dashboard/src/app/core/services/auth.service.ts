import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ApplicationConstants } from 'src/app/constants/application.constants';
import { UrlConstants } from 'src/app/constants/url.constants';
import { HttpRestService } from 'src/app/services/http.rest.service';
import { StoreProvider } from 'src/app/shared/utility/store';

@Injectable()
export class AuthService {

    constructor(public httpService: HttpRestService, private store: StoreProvider) {
    }

    login(userName: string, password: string): Observable<any> {
        return this.httpService.postService(UrlConstants.AUTH.LOGIN, { "username": userName, "password": password });
    }

    logout(payload: any): Observable<any> {
        this.store.removeAllItems();
        return this.httpService.postService(UrlConstants.AUTH.LOGOUT, payload);
    }

    // Need this for file-uploader
    getAuthToken(): string {
      return `Bearer ${this.store.getStoreItem(ApplicationConstants.ACCESS_TOKEN_KEY)}`;
    }

    getLoggedInUsername(): string {
      return this.store.getStoreItem(ApplicationConstants.ACTIVE_USER_USERNAME);
    }

}
