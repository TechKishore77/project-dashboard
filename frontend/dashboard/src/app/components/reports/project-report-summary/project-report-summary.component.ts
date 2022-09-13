import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-project-report-summary',
  templateUrl: './project-report-summary.component.html',
  styleUrls: ['./project-report-summary.component.scss']
})
export class ProjectReportSummaryComponent implements OnInit, OnChanges {
  projectId:number = 0;
  project:Partial<Project> = {};
  activeIndex:number = 0;

  constructor(private route: ActivatedRoute, private router: Router) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.project = {id: this.projectId};
  }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
    this.project = {id: this.projectId};
  }

  onClickSideNav(index: number) {
    let route = `project/${this.projectId}/project-report-advanced`;
    switch(index) {
      case 1:
        this.router.navigate([route], {queryParams: {tab: 'spiVariance'}});
        break;
      case 2:
        this.router.navigate([route], {queryParams: {tab: 'issues'}});
        break;
      case 3:
        this.router.navigate([route], {queryParams: {tab: 'drivingActivities'}});
        break;
      case 0:
      default:
        this.router.navigate([route], {queryParams: {tab: 'sCurve'}});
        break;
    }
    return false;
  }

}
