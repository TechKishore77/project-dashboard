import { Component, OnInit } from '@angular/core';
import { BudgetComponent } from 'src/app/model/budget-component';
import { OperatingBudget } from 'src/app/model/operating-budget';

@Component({
  selector: 'app-operating-budget-form',
  templateUrl: './operating-budget-form.component.html',
  styleUrls: ['./operating-budget-form.component.scss'],
  inputs: ['budget', 'isEditing'],
})
export class OperatingBudgetFormComponent implements OnInit {

  budget?: OperatingBudget
  isEditing: boolean = false
  components: BudgetComponent[] = []

  constructor() { }

  setDefaultComponents() {
    this.components = [
      {description: "Materials", cost: 0, isDefault: true},
      {description: "Man Power", cost: 0, isDefault: true},
      {description: "Sub Contract", cost: 0, isDefault: true},
      {description: "Equipment", cost: 0, isDefault: true},
      {description: "Site Expenses", cost: 0, isDefault: true},
    ]
  }

  ngOnInit(): void {
    this.setDefaultComponents();
    if(this.budget != null && this.isEditing) {
      if(this.budget.components.length > 0)
        this.components = this.budget.components;
    }
  }

  onAddBudgetComponent() {
    this.components.push({description: "", cost: 0, isDefault: false});
  }

  deleteItem(event: any, index: number) {
    this.components.splice(index, 1);
  }

}
