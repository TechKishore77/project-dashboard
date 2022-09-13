import { Component, Input, OnInit } from '@angular/core';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-payment-summary-dashboard',
  templateUrl: './payment-summary-dashboard.component.html',
  styleUrls: ['./payment-summary-dashboard.component.scss']
})
export class PaymentSummaryDashboardComponent implements OnInit {
  plannedInvoice:number = 0
  actualInvoice:number = 0

  @Input() project?:Project

  constructor() { }

  ngOnInit(): void {
  }

}
