
import {throwError as observableThrowError,  Observable ,  BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { catchError, finalize, map } from 'rxjs/operators';


@Injectable()
export class HTTPStatus {
    private loaderRequest$: BehaviorSubject<boolean>;
    constructor() {
        this.loaderRequest$ = new BehaviorSubject(<any>false);
    }

    setHttpStatus(inFlight: boolean) {
        this.loaderRequest$.next(inFlight);
    }

    getHttpStatus(): Observable<boolean> {
        return this.loaderRequest$.asObservable();
    }
}

@Injectable()
export class HTTPListener implements HttpInterceptor {
    constructor(private status: HTTPStatus) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            map(event => {
                this.status.setHttpStatus(true);
                return event;
            }),
            catchError(error => {
                this.status.setHttpStatus(false);
                return observableThrowError(error);
            }),
            finalize(() => {
                this.status.setHttpStatus(false);
            })
        )
    }
}