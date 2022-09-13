import { Component, OnChanges, OnInit, SimpleChanges, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Issue } from 'src/app/model/issue';
import { ProjectProfitData } from 'src/app/model/project-profit-data';
import { ProjectProgressData } from 'src/app/model/project-progress-data';
import { IssueService } from 'src/app/services/issue.service';
import { ProjectService } from 'src/app/services/project.service';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, OnChanges {

  projectProfit:ProjectProfitData[] = []
  projectProgress: ProjectProgressData[] = []
  modalRef!:BsModalRef;
  config = {
    backdrop: true,
    ignoreBackdropClick: true
  };
  isExistingProject:boolean = false;
  completeProjects = 0;
  overallIssues:Issue[] = []

  constructor(private projectService: ProjectService, private reportsService: ReportsService, private modalService: BsModalService, private _issueService:IssueService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.isExistingProject = false;
  }

  ngOnInit(): void {
    this.reportsService.getProgressReport().subscribe(projectProgressData => {
      this.projectProgress = projectProgressData;
      let complete = 0;
      this.projectProgress.forEach((project) => {
        if(project.actualProgress == 100) complete++;
      });
      this.completeProjects = complete;
    });
    this._issueService.getLatestIssuesOverall().subscribe(issues => {
      this.overallIssues = issues;
    });
    this.reportsService.getProfitReport().subscribe(projectProfitData => this.projectProfit = projectProfitData);
    this.isExistingProject = false;
  }

  openUploadModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
    return false;
  }

  toggleExisting(event:any) {
    this.isExistingProject = event.checked;
  }

  onSuccessfulUpload(event: any) {
    this.modalRef.hide();
  }

}
