import { Big } from 'big.js';
import { Invoice } from './invoice';

export class ProjectSummary {
  forecastCompletionDate: Date = new Date();
  plannedCompletionDate: Date = new Date();
  actualProgressPercent: Big = new Big(0);
  plannedProgressPercent: Big = new Big(0);
  dataDate: Date = new Date();
  latestInvoice: Invoice = new Invoice();
  plannedInvoice: Invoice = new Invoice();
  plannedProfit: Big = new Big(0);
  actualProfit: Big = new Big(0);
  percentChangeFromLastMonth: Big = new Big(0);
}
