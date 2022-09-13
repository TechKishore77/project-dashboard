import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private httpClient: HttpClient) { }

  handleError(error: HttpErrorResponse) {
    console.log("Error in FileUploadService: " + error);
  }

  postFile(fileToUpload: File): Observable<any> {
    const endpoint = 'localhost:8080/fileUpload';
    const formData: FormData = new FormData();
    const headers = new HttpHeaders();
    formData.append('excelFile', fileToUpload, fileToUpload.name);
    return this.httpClient
      .post(endpoint, formData, { headers: headers })
      .pipe(catchError(err => {
        return of(false);
      }
      ));

}
}
