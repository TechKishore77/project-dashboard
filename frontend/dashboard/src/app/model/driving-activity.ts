import { Project } from "./project";

export class DrivingActivity {
  id: number = 0;
  dataDate: Date = new Date();
  name: string = '';
  status: string = '';
  issues: string = '';
  actionParty: string = '';
  project: Project = new Project();
}
