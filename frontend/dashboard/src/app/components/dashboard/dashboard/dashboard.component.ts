import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from 'src/app/model/project';
import { ProjectService } from '../../../services/project.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  projectId:number = 0
  project?:Project

  constructor(private route: ActivatedRoute, private projectService:ProjectService) { }

  ngOnInit() {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
    this.projectService.getProjectDetails(this.projectId)?.subscribe(project => {this.project = project;
    });
  }

}
