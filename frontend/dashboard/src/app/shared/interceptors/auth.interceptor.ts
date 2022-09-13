import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import "rxjs/operator/do";
import { catchError, finalize, map, timeout } from 'rxjs/operators';
import { ApplicationConstants } from 'src/app/constants/application.constants';
import { AuthService } from 'src/app/core/services/auth.service';
import { NotificationService } from 'src/app/services/notification.service';
import { Router } from '../../../../node_modules/@angular/router';
import { StoreProvider } from '../utility/store';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private store: StoreProvider, private _router: Router, private _authService: AuthService, private _notifService: NotificationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler) {
        let loggedInUser: any = this.store.getStoreItem(ApplicationConstants.ACCESS_TOKEN_KEY);
        if (loggedInUser) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${loggedInUser}`
                }
            });
        }
        if (!request.headers.has('Content-Type')) {
            request = request.clone({
                headers: request.headers.set('Content-Type', 'application/json'),
            });
        }

        if (!request.headers.has('Access')) {
            request = request.clone({
                headers: request.headers.set('Access-Control-Allow-Origin', '*')
            })
        }

        request = request.clone({
            headers: request.headers.set('Accept', 'application/json'),
        });
        return next.handle(request).pipe(
            timeout(900000),
            map(
                (event: HttpEvent<any>) => {
                    return event;
                }
            ),

            catchError((error: any) => {
              if(error instanceof HttpErrorResponse) {
                if ([401, 403].indexOf(error.status) !== -1) {
                  // auto logout if 401 Unauthorized or 403 Forbidden response returned from api
                  this._authService.logout({});
                  return throwError(error);
                }
              }
              return throwError(error);
            }),

            finalize(() => {})
        );
    }
}
