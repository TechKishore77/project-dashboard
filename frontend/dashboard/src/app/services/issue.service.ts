import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Issue } from '../model/issue';
import { Project } from '../model/project';
import { HttpRestService } from './http.rest.service';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private httpService: HttpRestService) { }

  getIssues(project?: Project) : Observable<Issue[]> {
    if(project && project.id != 0)
      return this.httpService.getService(`/project/${project.id}/issue`);
    return of([]);
  }

  getLatestIssuesOverall(): Observable<Issue[]> {
    return this.httpService.getService("/issues");
  }

  deleteIssue(issue: Issue|undefined): Observable<any> {
    if(issue)
      return this.httpService.deleteService(`/issue/${issue.id}`);
    return of();
  }
}
