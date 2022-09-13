import { BudgetComponent } from './budget-component';

export class AnticipatedBudget {
  id: number = 0;
  components: BudgetComponent[] = [];
  dataDate: Date = new Date();
  totalBudget: number = 0;
}
