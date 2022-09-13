import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UrlConstants } from '../constants/url.constants';

@Injectable({
  providedIn: 'root'
})
export class UpdateService {

  constructor(private httpClient: HttpClient) { }

  paymentUrl:string =  UrlConstants.AUTH.SERVER_URL + "/project/payment";

  updateSchedule() {

  }

  updateAnticipatedBudget() {

  }

  updateMonthlyPayment(data:any) {
    this.httpClient.post(this.paymentUrl, data);
  }
}
