export class SCurveData {
  months: string[];
  plannedValues: (number | null)[];
  actualValues: (number | null)[];
  cumulativePlannedPercentValues: (number | null)[];
  cumulativeActualPercentValues: (number | null)[];
  maxValue: number;

  constructor() {
    this.months = [];
    this.plannedValues = [];
    this.actualValues = [];
    this.cumulativeActualPercentValues = [];
    this.cumulativePlannedPercentValues = [];
    this.maxValue = 0;
  }
}
