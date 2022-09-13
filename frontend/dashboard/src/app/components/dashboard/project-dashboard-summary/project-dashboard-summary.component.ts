import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-project-dashboard-summary',
  templateUrl: './project-dashboard-summary.component.html',
  styleUrls: ['./project-dashboard-summary.component.scss']
})
export class ProjectDashboardSummaryComponent implements OnInit, OnChanges {

  @Input() project?:Project

  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {
  }

}
