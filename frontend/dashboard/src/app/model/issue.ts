import { Project } from "./project";

export class Issue {
  id: number = 0;
  createdOn: Date = new Date();
  reportingDate: Date = new Date();
  description: string = '';
  plannedCloseDate: Date = new Date();
  actionParty: String = '';
  status: string = "Open";
  project: Project = new Project();
}
