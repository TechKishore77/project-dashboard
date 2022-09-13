import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AnticipatedBudget } from 'src/app/model/anticipated-budget';

@Component({
  selector: 'app-anticipated-budget-summary',
  templateUrl: './anticipated-budget-summary.component.html',
  styleUrls: ['./anticipated-budget-summary.component.scss'],
  inputs: ['budget']
})
export class AnticipatedBudgetSummaryComponent implements OnInit {

  budget?:AnticipatedBudget
  @Output() edit = new EventEmitter<AnticipatedBudget>();

  constructor() { }

  ngOnInit(): void {
  }

  onEdit() {
    this.edit.emit(this.budget);
  }

}
