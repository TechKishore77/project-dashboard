import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ForecastCompletionData } from 'src/app/model/forecast-completion-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-completion-project-tile',
  templateUrl: './completion-project-tile.component.html',
  styleUrls: ['./completion-project-tile.component.scss']
})
export class CompletionProjectTileComponent implements OnInit, OnChanges {
  @Input() projectId:number = 0
  data: ForecastCompletionData = new ForecastCompletionData();

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getForecastCompletionReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

  ngOnInit(): void {
    this.reportsService.getForecastCompletionReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

}
