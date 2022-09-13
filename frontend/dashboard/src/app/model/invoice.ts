import Big from 'big.js';
export class Invoice {
  dataDate: Date = new Date();
  invoiceAmount: Big = new Big(0);
  certifiedAmount: Big = new Big(0);
}
