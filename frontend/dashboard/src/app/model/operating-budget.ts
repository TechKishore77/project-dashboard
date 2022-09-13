import { Big } from 'big.js';
import { BudgetComponent } from './budget-component';

export class OperatingBudget {
  components: BudgetComponent[] = [];
  id: number = 0;
  totalBudget: Big = new Big(0);
}
