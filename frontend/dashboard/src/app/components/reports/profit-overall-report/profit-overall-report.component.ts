import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectProfitData } from 'src/app/model/project-profit-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-profit-overall-report',
  templateUrl: './profit-overall-report.component.html',
  styleUrls: ['./profit-overall-report.component.scss']
})
export class ProfitOverallReportComponent implements OnInit, OnChanges {
  report: ProjectProfitData[] = []

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getProfitReport().subscribe(report => {
      this.report = report;
    });
  }

  ngOnInit(): void {
    this.reportsService.getProfitReport().subscribe(report => {
      this.report = report;
    });
  }

}
