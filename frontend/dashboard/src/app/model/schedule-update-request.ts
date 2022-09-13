import { DrivingActivity } from './driving-activity';
export class ScheduleUpdateRequest {
  dataDate: Date = new Date();
  progressPercent: number = 0;
  forecastCompletionDate: Date = new Date();
  drivingActivities: DrivingActivity[] = [];
}
