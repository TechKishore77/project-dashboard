import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { DrivingActivity } from '../model/driving-activity';
import { Project } from '../model/project';
import { HttpRestService } from './http.rest.service';

@Injectable({
  providedIn: 'root'
})
export class DrivingActivityService {

  constructor(private httpService: HttpRestService) { }

  getDrivingActivities(project?:Project) : Observable<DrivingActivity[]>{
    if(project && project.id != 0)
      return this.httpService.getService(`/project/${project.id}/drivingActivity`);
    return of([]);
  }
}
