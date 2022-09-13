import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectProgressData } from 'src/app/model/project-progress-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-progress-overall-report',
  templateUrl: './progress-overall-report.component.html',
  styleUrls: ['./progress-overall-report.component.scss']
})
export class ProgressOverallReportComponent implements OnInit, OnChanges {
  report: ProjectProgressData[] = []

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getProgressReport().subscribe(report => {
      this.report = report;
    });
  }

  ngOnInit(): void {
    this.reportsService.getProgressReport().subscribe(report => {
      this.report = report;
    });
  }

}
