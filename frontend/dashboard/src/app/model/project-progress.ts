import { Big } from 'big.js';

export class ProjectProgress {
  dataDate: Date = new Date();
  progressPercentage: Big = new Big(0);
  forecastCompletionDate: Date = new Date();
}
