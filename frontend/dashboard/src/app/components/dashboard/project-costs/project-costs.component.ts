import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { AnticipatedBudget } from 'src/app/model/anticipated-budget';
import { OperatingBudget } from 'src/app/model/operating-budget';
import { Project } from 'src/app/model/project';
import { BudgetService } from 'src/app/services/budget.service';

@Component({
  selector: 'app-project-costs',
  templateUrl: './project-costs.component.html',
  styleUrls: ['./project-costs.component.scss']
})
export class ProjectCostsComponent implements OnInit, OnChanges {
  @Input() project?:Project
  operatingBudget?:OperatingBudget
  anticipatedBudget?:AnticipatedBudget
  profit:number = 0
  achievedProfit:number = 0

  constructor(private budgetService:BudgetService) { }
  ngOnChanges(changes: SimpleChanges): void {
    if(this.project != null) {
      this.budgetService.getOperatingBudget(this.project.id).subscribe(operatingBudget => this.operatingBudget = operatingBudget);
      this.budgetService.getAnticipatedBudget(this.project.id).subscribe(anticipatedBudget => this.anticipatedBudget = anticipatedBudget);
    }
  }

  ngOnInit(): void {
    if(this.project != null) {
      this.budgetService.getOperatingBudget(this.project.id).subscribe(operatingBudget => this.operatingBudget = operatingBudget);
      this.budgetService.getAnticipatedBudget(this.project.id).subscribe(anticipatedBudget => this.anticipatedBudget = anticipatedBudget);
    }
  }

}
