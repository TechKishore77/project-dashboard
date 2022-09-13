import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ForecastCompletionData } from 'src/app/model/forecast-completion-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-completion-overall-report',
  templateUrl: './completion-overall-report.component.html',
  styleUrls: ['./completion-overall-report.component.scss']
})
export class CompletionOverallReportComponent implements OnInit, OnChanges {
  report: ForecastCompletionData[] = []

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getForecastCompletionReport().subscribe(report => {
      this.report = report;
    });
  }

  ngOnInit(): void {
    this.reportsService.getForecastCompletionReport().subscribe(report => {
      this.report = report;
    });
  }

}
