import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UrlConstants } from '../constants/url.constants';
import { ForecastCompletionData } from '../model/forecast-completion-data';
import { ProjectPaymentData } from '../model/project-payment-data';
import { ProjectProfitData } from '../model/project-profit-data';
import { ProjectProgressData } from '../model/project-progress-data';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  reportsRoot = `${UrlConstants.AUTH.SERVER_URL}/reports`;

  constructor(private httpClient: HttpClient) { }

  getForecastCompletionReport():Observable<ForecastCompletionData[]> {
    return this.httpClient.get<ForecastCompletionData[]>(`${this.reportsRoot}/forecastCompletion`);
  }

  getProgressReport():Observable<ProjectProgressData[]> {
    return this.httpClient.get<ProjectProgressData[]>(`${this.reportsRoot}/progress`);
  }

  getProfitReport():Observable<ProjectProfitData[]> {
    return this.httpClient.get<ProjectProfitData[]>(`${this.reportsRoot}/profit`);
  }

  getPaymentReport():Observable<ProjectPaymentData[]> {
    return this.httpClient.get<ProjectPaymentData[]>(`${this.reportsRoot}/payment`);
  }

  getForecastCompletionReportForProject(projectId:number):Observable<ForecastCompletionData>|null {
    if(projectId != 0)
      return this.httpClient.get<ForecastCompletionData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/reports/forecastCompletion`);
    return null;
  }

  getProgressReportForProject(projectId: number):Observable<ProjectProgressData> | null{
    if(projectId != 0)
      return this.httpClient.get<ProjectProgressData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/reports/progress`);
    return null;
  }

  getProfitReportForProject(projectId:number):Observable<ProjectProfitData> | null {
    if(projectId != 0)
      return this.httpClient.get<ProjectProfitData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/reports/profit`);
    return null;
  }

  getPaymentReportForProject(projectId:number):Observable<ProjectPaymentData> | null {
    if(projectId != 0)
      return this.httpClient.get<ProjectPaymentData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/reports/payment`);
    return null;
  }

}
