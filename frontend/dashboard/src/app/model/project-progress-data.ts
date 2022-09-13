import { Project } from "./project";

export class ProjectProgressData {
  project: Project | null = null;
  actualProgress: number = 0;
  plannedProgress: number = 0;
  dataDate: Date | null = null;
}
