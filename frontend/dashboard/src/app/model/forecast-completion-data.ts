import { Project } from "./project";

export class ForecastCompletionData {
  project: Project | null = null;
  forecastCompletionDate: Date | null = null;
  plannedCompletionDate: Date | null = null;
  delayDays: number = 0;
}
