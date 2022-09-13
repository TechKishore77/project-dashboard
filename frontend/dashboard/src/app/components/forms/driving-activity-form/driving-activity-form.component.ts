import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DrivingActivity } from 'src/app/model/driving-activity';
import { HttpRestService } from 'src/app/services/http.rest.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-driving-activity-form',
  templateUrl: './driving-activity-form.component.html',
  styleUrls: ['./driving-activity-form.component.scss'],
})
export class DrivingActivityFormComponent implements OnInit, OnChanges {

  statusOptions:string[] = ["Started", "Not Started", "In Progress"];
  actionPartyOptions:string[] = ["Client", "Project Manager", "Procurement", "Finance", "Site Team", "Engineering"];

  @Input() drivingActivity?:DrivingActivity;
  @Input() isEditing?:boolean = false;
  @Input() editingFromReports:boolean = false;
  @Output() addRequest = new EventEmitter<DrivingActivity>();
  @Output() editActivity = new EventEmitter<DrivingActivity>();
  @Output() editFailed = new EventEmitter<boolean>();
  drivingActivityForm:any;
  projectId:number = 0;

  constructor(private route: ActivatedRoute, private notificationService: NotificationService, private _formBuilder: FormBuilder,private httpRestService: HttpRestService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();
  }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0

    if(this.isEditing && this.drivingActivity) {
      this.drivingActivityForm = this._formBuilder.group({
        name: [this.drivingActivity.name, Validators.required],
        status: [this.statusOptions[this.statusOptions.indexOf(this.drivingActivity.status.toString())], Validators.required],
        issues: [this.drivingActivity.issues, Validators.required],
        actionParty: [this.actionPartyOptions[this.actionPartyOptions.indexOf(this.drivingActivity.actionParty.toString())], Validators.required],
      });
    } else {
      this.drivingActivityForm = this._formBuilder.group({
        name: [null, Validators.required],
        status: [this.statusOptions[0], Validators.required],
        issues: [null, Validators.required],
        actionParty: [this.actionPartyOptions[0], Validators.required]
      });
    }
  }

  onAdd() {
    if(this.drivingActivityForm.valid) {
      if(this.isEditing && this.editingFromReports && this.drivingActivity) {
        let formData = this.drivingActivityForm.value;
        formData.dataDate = this.drivingActivity.dataDate;
        formData.id = this.drivingActivity.id;
        formData.project = this.drivingActivity.project;
        this.httpRestService.putService(`/drivingActivity/${this.drivingActivity.id}`, formData).subscribe(data => {
          this.notificationService.successMessage("Driving Activity updated successfully");
          let activity:DrivingActivity = formData;
          this.editActivity.emit(activity);
        },
        (error) => {
          this.editFailed.emit(true);
        });
        this.drivingActivityForm.reset();
      }
      let activity = this.drivingActivityForm.value;
      this.addRequest.emit(activity);
      this.drivingActivityForm.reset();
      this.drivingActivityForm.patchValue({
        status: this.statusOptions[0],
        actionParty: this.actionPartyOptions[0]
      });
    } else {
      this.notificationService.showError("Please fill all fields");
    }
  }

}
