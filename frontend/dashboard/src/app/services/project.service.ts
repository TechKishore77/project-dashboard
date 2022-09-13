import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { UrlConstants } from '../constants/url.constants';
import { Project } from '../model/project';
import { HttpRestService } from './http.rest.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  projectGetAllUrl: string = `${UrlConstants.AUTH.SERVER_URL}/project/`;

  constructor(private httpClient: HttpClient, private restService: HttpRestService) {}

  getProjects() {
    return this.httpClient.get<Project[]>(this.projectGetAllUrl);
  }

  getProjectDetails(projectId: number) {
    if(projectId != 0) {
    return this.httpClient.get<Project>(`${this.projectGetAllUrl}${projectId}`);
    }
    return null;
  }

  deleteProject(project: Project) {
    if(project && project.id) {
      return this.restService.deleteService(`/project/${project.id}`);
    }
    return of();
  }
}
