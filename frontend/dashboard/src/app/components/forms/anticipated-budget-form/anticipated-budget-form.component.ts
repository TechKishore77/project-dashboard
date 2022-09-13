import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { AnticipatedBudget } from 'src/app/model/anticipated-budget';
import { BudgetComponent } from 'src/app/model/budget-component';
import { HttpRestService } from 'src/app/services/http.rest.service';

@Component({
  selector: 'app-anticipated-budget-form',
  templateUrl: './anticipated-budget-form.component.html',
  styleUrls: ['./anticipated-budget-form.component.scss'],
  inputs: ['budget', 'isEditing'],
})
export class AnticipatedBudgetFormComponent implements OnInit {

  budget?: AnticipatedBudget
  isEditing: boolean = false;
  components: BudgetComponent[] = []
  @Output() updateBudget = new EventEmitter<AnticipatedBudget>();
  projectId:number = 0;
  dataDate:FormControl = new FormControl(null, [Validators.required]);
  bsConfig: Partial<BsDatepickerConfig>;

  constructor(private httpService: HttpRestService, private route: ActivatedRoute) {
    this.bsConfig = Object.assign({}, { maxDate: new Date() });
  }

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
      if(this.budget.components.length > 0) {
        this.components = this.budget.components;
      }
    }
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
  }

  calculateTotalBudget() : number {
    let total:number = 0
    for(let i=0; i<this.components.length; i++) {
      total += this.components[i].cost;
    }
    return total;
  }

  onAddBudgetComponent() {
    this.components.push({description: "", cost: 0, isDefault: false});
  }

  deleteItem(event: any, index: number) {
    this.components.splice(index, 1);
    if(this.budget != null)
    this.budget.totalBudget = this.calculateTotalBudget();
  }

  onChange(component:BudgetComponent, index: number) {
    this.components[index] = component;
    if(this.budget == null)
      this.budget = new AnticipatedBudget();
    this.budget.components = this.components;
    this.budget.totalBudget = this.calculateTotalBudget();
  }

  onSubmit() {
    if(this.budget == null) this.budget = new AnticipatedBudget();
    this.budget.components = this.components;
    this.updateBudget.emit(this.budget);
    if(this.dataDate.valid) {
      this.budget.dataDate = this.dataDate.value;
      this.httpService.postService(`/project/${this.projectId}/anticipatedBudget`, this.budget).subscribe(resp => console.log(resp));
      this.budget = new AnticipatedBudget();
      this.setDefaultComponents();
    }
  }

}
