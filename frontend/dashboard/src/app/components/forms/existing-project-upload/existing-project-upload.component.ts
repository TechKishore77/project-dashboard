import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UrlConstants } from 'src/app/constants/url.constants';
import { Project } from 'src/app/model/project';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-existing-project-upload',
  templateUrl: './existing-project-upload.component.html',
  styleUrls: ['./existing-project-upload.component.scss']
})
export class ExistingProjectUploadComponent implements OnInit {
  projects:Project[] = []
  config = {displayKey: "contractTitle",search:true, placeholder: "Select Project to update"};
  selectedProject?:Project
  fileUploadEnabled:boolean = false
  projectUpdateURL:string = ""
  serverURL:string = UrlConstants.AUTH.SERVER_URL;
  constructor(public projectService: ProjectService) { }
  @Output() success: EventEmitter<boolean> = new EventEmitter<boolean>();

  ngOnInit(): void {
    this.projectService.getProjects().subscribe(projects => {
      this.projects = projects;
    });
  }

  selectionChanged(item:any) {
    if(item.value != null) {
      this.selectedProject = item.value;
      this.fileUploadEnabled = true;
      this.projectUpdateURL = `${this.serverURL}/project/${this.selectedProject?.id}/uploadExcel`
    }
  }

  onSuccess(val:boolean) {
    this.success.emit(val);
  }

}
