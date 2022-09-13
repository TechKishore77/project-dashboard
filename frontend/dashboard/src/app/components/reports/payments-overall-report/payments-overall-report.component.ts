import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectPaymentData } from 'src/app/model/project-payment-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-payments-overall-report',
  templateUrl: './payments-overall-report.component.html',
  styleUrls: ['./payments-overall-report.component.scss']
})
export class PaymentsOverallReportComponent implements OnInit, OnChanges {
  report: ProjectPaymentData[] = []

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getPaymentReport().subscribe(report => {
      this.report = report;
    });
  }

  ngOnInit(): void {
    this.reportsService.getPaymentReport().subscribe(report => {
      this.report = report;
    });
  }

}
