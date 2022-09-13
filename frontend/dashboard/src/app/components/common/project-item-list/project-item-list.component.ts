import { ChangeDetectorRef, Component, Input, OnInit, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Project } from 'src/app/model/project';
import { NotificationService } from 'src/app/services/notification.service';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-project-item-list',
  templateUrl: './project-item-list.component.html',
  styleUrls: ['./project-item-list.component.scss']
})
export class ProjectItemListComponent implements OnInit {
  projectService: ProjectService;
  projects: Project[] = []
  @Input() itemRouteSuffix:string = "/dashboard"
  @Input() title: string = "Projects"
  @Input() action: string = "View"
  errorMessage?:string
  loading?:boolean = true

  constructor(projectService: ProjectService, private _changeRef: ChangeDetectorRef, private modalService: NgbModal, private _notifService: NotificationService) {
    this.projectService = projectService;
  }

  ngOnInit(): void {
    this.projectService.getProjects().subscribe(
      projects => {
      this.projects = projects;
      this.errorMessage = undefined;
      this.loading = false;
    },
    (error: any) => {
      this.errorMessage = "Error loading projects";
      this.loading = false;
    }
    );
  }

  onDeleteProject(confirmDg: TemplateRef<any>, project: Project) {
    this.modalService.open(confirmDg, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      console.log(`${result} clicked`);
      if(result == 'Yes') {
        this.projectService.deleteProject(project).subscribe((res) => {
          this._notifService.successMessage("Project deleted successfully");
          let index = this.projects.findIndex(proj => project.id == project.id);
          if(index != -1) {
            this.projects.splice(index, 1);
            this._changeRef.detectChanges();
          }
        }, (err)=> {
          this._notifService.showError("Failed to delete project. Please try again later.");
        });
      }
    });
  }

}
