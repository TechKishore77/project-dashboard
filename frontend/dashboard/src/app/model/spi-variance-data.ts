export class SpiVarianceData {
  months: string[];
  spiData: (number | null)[];
  varianceData: (number|null)[];

  constructor() {
    this.months = []
    this.spiData = []
    this.varianceData = []
  }
}
