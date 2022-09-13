import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faChevronLeft } from '@fortawesome/free-solid-svg-icons';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-project-report-advanced',
  templateUrl: './project-report-advanced.component.html',
  styleUrls: ['./project-report-advanced.component.scss']
})
export class ProjectReportAdvancedComponent implements OnInit {
  @ViewChild('tabs', { static: false }) staticTabs!: TabsetComponent;
  projectId:number = 0;
  project?:Project;
  faChevronLeft=faChevronLeft;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
    this.project = new Project();
    this.project.id = this.projectId;
  }

  ngAfterViewInit(): void {
    let tabSelected:(string|null) = this.route.snapshot.queryParamMap.get('tab');
    if(tabSelected != null) {
      if(tabSelected == "spiVariance")
        this.selectTab(1);
      else if(tabSelected == "issues")
        this.selectTab(2);
      else if(tabSelected == "drivingActivities")
        this.selectTab(3);
      else
        this.selectTab(0);
    }
  }

  selectTab(tabIndex: number) {
    this.staticTabs.tabs[tabIndex].active = true;
  }

  onBack() {
    this.router.navigate([`/project/${this.projectId}/project-summary-report`]);
  }

}
