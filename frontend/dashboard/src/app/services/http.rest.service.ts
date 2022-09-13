import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { UrlConstants } from '../constants/url.constants';

@Injectable({
  providedIn: 'root'
})
export class HttpRestService {
    public url = '';
    public body: {} = {};
    public httpOptions!: {};
    public httpFormOptions!: {};

    constructor(private httpClient: HttpClient) {
        this.setHeaders();
    }

    setHeaders() {
        let headers = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json')
            .append('Access-Control-Allow-Origin', '*');
        return this.httpOptions = {
            headers
        };
    }

    setFormHeaders() {
        let formHeaders = new HttpHeaders();
        formHeaders = formHeaders.append('Content-Type', 'multipart/form-data')
            .append('Access-Control-Allow-Origin', '*');
        return this.httpFormOptions = {
            formHeaders
        };
    }

    getService(apiURL: string) {
        this.url = UrlConstants.AUTH.SERVER_URL + apiURL;
        return this.httpClient.get(this.url, this.httpOptions)
            .pipe(
                map((response: any) => response)
                , catchError((e) => {
                    return throwError(e);
                })
            );
    }

    postService(apiURL: string, data: {}) {
        this.url = UrlConstants.AUTH.SERVER_URL + apiURL;
        return this.httpClient.post(this.url, data, this.httpOptions)
            .pipe(
                map((response: any) => response)
                , catchError((e) => {
                  return throwError(e);
                })
            );
    }

    putService(apiURL: string, data: {}) {
        this.url = UrlConstants.AUTH.SERVER_URL + apiURL;
        return this.httpClient.put(this.url, data, this.httpOptions)
            .pipe(
                map((response: any) => response)
                , catchError((e) => {
                  return throwError(e);
                })
            );
    }

    deleteService(apiURL: string) {
        this.url = UrlConstants.AUTH.SERVER_URL + apiURL;
        return this.httpClient.delete(this.url, this.httpOptions)
            .pipe(
                map((response: any) => response)
                , catchError((e) => {
                  return throwError(e);
                })
            );
    }

}
