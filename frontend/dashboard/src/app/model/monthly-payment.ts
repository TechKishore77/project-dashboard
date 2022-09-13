import { Project } from "./project";

export interface MonthlyPayments {
    ipcNo: number;
    valueDate: Date;
    submittedDate: Date;
    grossAmount: string;
    accumulatedValAmount: string;
    netPayableAmount: string;
    certifiedDate: Date;
    certifiedAmount: string;
    paymentDueDate: Date;
    receivableAmount: Date;
    receivedAmount: number;
    receivedDate: Date;
    remarks: string;
    projectInfo: Project
}
