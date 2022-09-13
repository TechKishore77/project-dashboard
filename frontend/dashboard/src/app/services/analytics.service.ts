import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { UrlConstants } from '../constants/url.constants';
import { SCurveData } from '../model/s-curve-data';
import { SpiVarianceData } from '../model/spi-variance-data';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor(private httpClient: HttpClient) { }

  getSCurveData(projectId: (number|undefined)) : Observable<SCurveData> {
    if(projectId && projectId != 0)
      return this.httpClient.get<SCurveData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/sCurveData`);
    return of({months: [], plannedValues: [], actualValues: [], cumulativeActualPercentValues: [], cumulativePlannedPercentValues: [], maxValue: 0});
  }

  getSpiVarianceData(projectId: (number|undefined)) : Observable<SpiVarianceData> {
    if(projectId && projectId != 0)
      return this.httpClient.get<SpiVarianceData>(`${UrlConstants.AUTH.SERVER_URL}/project/${projectId}/spiVarianceData`);
    return of({months: [], spiData:[], varianceData: []});
  }
}
