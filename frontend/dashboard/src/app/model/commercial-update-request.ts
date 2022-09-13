import Big from 'big.js';
import { AnticipatedBudget } from './anticipated-budget';

export class CommercialUpdateRequest {
  invoiceDate: Date = new Date();
  latestInvoiceAmount: Big = new Big(0);
  certifiedAmount: Big = new Big(0);
  profit: Big = new Big(0);
  anticipatedBudget: AnticipatedBudget = new AnticipatedBudget();
}
