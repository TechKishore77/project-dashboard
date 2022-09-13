import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { BudgetComponent } from 'src/app/model/budget-component';

@Component({
  selector: 'app-budget-component-input',
  templateUrl: './budget-component-input.component.html',
  styleUrls: ['./budget-component-input.component.scss'],
  inputs: ['component', 'index']
})
export class BudgetComponentInputComponent implements OnInit {

  @Output() deleteRequest = new EventEmitter<number>();
  @Output() change = new EventEmitter<BudgetComponent>();
  component?: BudgetComponent
  faTrash = faTrash
  index:number = -1
  display = ""
  name:FormControl = new FormControl('Component Name')
  cost:FormControl = new FormControl('0')
  constructor() { }

  ngOnInit(): void {
    this.name.setValue(this.component?.description);
    this.cost.setValue(this.component?.cost);
  }

  onDelete() {
    this.deleteRequest.emit(this.index);
  }

  onChangeName(event:any) {
    event.stopPropagation();
    if(this.component == null) this.component = new BudgetComponent();
    this.component.description = this.name.value;
    this.change.emit(this.component);
  }

  onChangeCost(event:any) {
    event.stopPropagation();
    if(this.component != null) {
      this.component.cost = this.cost.value;
      this.change.emit(this.component);
    }
  }
}
