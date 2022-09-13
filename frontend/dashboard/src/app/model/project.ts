import { ProjectProperty } from './project-property';
export class Project {
  id: number = 0;
  contractNo: string = "";
  contractTitle: string = "";
  client: string = "";
  consultant: string = "";
  typeOfContract: string = "";
  formOfContract: string = "";
  contractValue: number = 0;
  startDate: Date | null = null;
  completionDate: Date | null = null;
  duration: number = 0;
  maintenancePeriod: number=0;
  status: string = "";
  createdOn: Date | null = null;
  properties: ProjectProperty[] = [];
}
