import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectProgressData } from 'src/app/model/project-progress-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-progress-project-tile',
  templateUrl: './progress-project-tile.component.html',
  styleUrls: ['./progress-project-tile.component.scss']
})
export class ProgressProjectTileComponent implements OnInit, OnChanges {
  @Input() projectId:number = 0
  data: ProjectProgressData = new ProjectProgressData();

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getProgressReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

  ngOnInit(): void {
    this.reportsService.getProgressReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

}
