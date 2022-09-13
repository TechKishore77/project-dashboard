import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Issue } from 'src/app/model/issue';
import { HttpRestService } from 'src/app/services/http.rest.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-issue-form',
  templateUrl: './issue-form.component.html',
  styleUrls: ['./issue-form.component.scss']
})
export class IssueFormComponent implements OnInit {
  @Input() issue?:Issue;
  @Input() isEditing:boolean = false;
  @Output() editIssue = new EventEmitter<Issue>();
  @Output() editFailed = new EventEmitter<boolean>();

  projectId:number = 0;
  issueForm:any;
  actionPartyOptions:string[] = ["Client", "Project Manager", "Procurement", "Finance", "Site Team", "Engineering"];

  constructor(private route: ActivatedRoute, private _formBuilder: FormBuilder, private httpRestService: HttpRestService, private _notifService: NotificationService) { }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0

      if(this.isEditing && this.issue) {
        this.issueForm = this._formBuilder.group({
          reportingDate: [new Date(this.issue.reportingDate), Validators.required],
          description: [this.issue.description, Validators.required],
          plannedCloseDate: [new Date(this.issue.plannedCloseDate), Validators.required],
          actionParty: [this.actionPartyOptions[this.actionPartyOptions.indexOf(this.issue.actionParty.toString())], Validators.required],
          status: [this.issue.status, [Validators.required]]
        });
      } else {
        this.issueForm = this._formBuilder.group({
          reportingDate: [new Date(), Validators.required],
          description: [null, Validators.required],
          plannedCloseDate: [new Date(), Validators.required],
          actionParty: [this.actionPartyOptions[0], Validators.required],
          status: ["Open", [Validators.required]]
        });
      }
  }

  reportIssue() {
    if(this.issueForm.valid) {
      if(this.isEditing && this.issue) {
        let formData = this.issueForm.value;
        formData.id = this.issue.id;
        formData.project = this.issue.project;
        this.httpRestService.putService(`/issue/${this.issue.id}`, formData).subscribe(data => {
          this._notifService.successMessage("Issue updated successfully");
          let issue:Issue = formData;
          this.editIssue.emit(issue);
        },
        (error) => {
          this.editFailed.emit(true);
        });
        this.issueForm.reset();
      } else {
        let formData = this.issueForm.value;
        this.httpRestService.postService(`/project/${this.projectId}/issue`, formData).subscribe(data => {
          this._notifService.successMessage("Issue reported successfully");
        });
        this.issueForm.reset();
        this.issueForm.patchValue({
          reportingDate: new Date(),
          plannedCloseDate: new Date(),
          actionParty: this.actionPartyOptions[0],
          status: "Open"
        });
      }
    }
  }

}
