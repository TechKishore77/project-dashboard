import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UrlConstants } from '../constants/url.constants';
import { AnticipatedBudget } from '../model/anticipated-budget';
import { OperatingBudget } from '../model/operating-budget';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {


  constructor(private httpClient: HttpClient) {}

  getOperatingBudget(projectId: number) {
    let operatingBudgetUrl = `${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/operatingBudget`
    return this.httpClient.get<OperatingBudget>(operatingBudgetUrl);
  }

  getAnticipatedBudget(projectId: number) {
    let operatingBudgetUrl = `${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/anticipatedBudget`
    return this.httpClient.get<AnticipatedBudget>(operatingBudgetUrl);
  }
}
