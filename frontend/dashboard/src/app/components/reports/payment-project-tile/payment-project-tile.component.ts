import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectPaymentData } from 'src/app/model/project-payment-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-payment-project-tile',
  templateUrl: './payment-project-tile.component.html',
  styleUrls: ['./payment-project-tile.component.scss']
})
export class PaymentProjectTileComponent implements OnInit, OnChanges {
  @Input() projectId: number = 0
  data: ProjectPaymentData = new ProjectPaymentData();
  variance: number = 0;

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getPaymentReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
      if(this.data && data.cumulativePlannedAmount != 0)
        this.variance = ((data.cumulativeReceivedAmount - data.cumulativePlannedAmount) / data.cumulativePlannedAmount ) * 100;
    });
  }

  ngOnInit(): void {
    this.reportsService.getPaymentReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

}
