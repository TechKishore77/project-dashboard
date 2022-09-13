import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { UrlConstants } from 'src/app/constants/url.constants';
import { HttpRestService } from 'src/app/services/http.rest.service';

@Injectable()
export class UserService {

    constructor(public httpService: HttpRestService) {
    }

    registerUser(payload: any): Observable<any> {
        return this.httpService.postService(UrlConstants.USER.REGISTER, payload);
    }

    logoutUser() {
        return this.httpService.postService(UrlConstants.AUTH.LOGOUT, {});
    }

}
