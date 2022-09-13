import { Project } from "./project";

export class ProjectPaymentData {
  project: Project | null = null;
  earnedValue: number = 0;
  cumulativePlannedAmount: number = 0;
  valueDate: Date | null = null;
  submittedDate: Date | null = null;
  latestGrossAmount: number = 0;
  latestReceivedAmount: number = 0;
  lastReceivedDate: Date | null = null;
  accumulatedGrossAmount: number = 0;
  cumulativeReceivedAmount: number = 0;
}
