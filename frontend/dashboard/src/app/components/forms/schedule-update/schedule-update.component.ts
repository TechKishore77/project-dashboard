import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { DrivingActivity } from 'src/app/model/driving-activity';
import { ForecastCompletionData } from 'src/app/model/forecast-completion-data';
import { ScheduleUpdateRequest } from 'src/app/model/schedule-update-request';
import { HttpRestService } from 'src/app/services/http.rest.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-schedule-update',
  templateUrl: './schedule-update.component.html',
  styleUrls: ['./schedule-update.component.scss']
})
export class ScheduleUpdateComponent implements OnInit {

  projectId:number = 0
  drivingActivities:DrivingActivity[] = []
  drivingActivityToEdit?:DrivingActivity
  isEditing?:boolean = false
  dataDate = new FormControl('')
  progressPercentage = new FormControl(0)
  forecastCompletionDate = new FormControl('')
  bsConfig: Partial<BsDatepickerConfig>;
  forecastCompletionData?:ForecastCompletionData;

  constructor(public ref: ChangeDetectorRef, private httpService: HttpRestService, private route: ActivatedRoute, private notifService: NotificationService) {
    this.bsConfig = Object.assign({}, { maxDate: new Date() });
  }

  loadForecastCompletionData() {
    if(this.projectId != 0) {
      this.httpService.getService(`/project/${this.projectId}/reports/forecastCompletion`).subscribe((forecastCompletionData) => {
        this.forecastCompletionData = forecastCompletionData;
      });
    }
  }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
    this.loadForecastCompletionData();
  }

  onAddActivity(activity: DrivingActivity) {
    this.drivingActivities = [...this.drivingActivities, activity];
  }

  onPostUpdate() {
    if(this.dataDate.valid && this.forecastCompletionDate.valid && this.progressPercentage.valid) {
      let scheduleUpdateReq = new ScheduleUpdateRequest();
      scheduleUpdateReq.dataDate = this.dataDate.value;
      scheduleUpdateReq.forecastCompletionDate = this.forecastCompletionDate.value;
      scheduleUpdateReq.progressPercent = this.progressPercentage.value;
      for(let i =0; i<this.drivingActivities.length; i++) {
        this.drivingActivities[i].dataDate = this.dataDate.value;
      }
      scheduleUpdateReq.drivingActivities = this.drivingActivities;
      this.httpService.postService(`/project/${this.projectId}/scheduleUpdate`, scheduleUpdateReq).subscribe(data => {
        this.drivingActivities = []
        this.dataDate.reset();
        this.progressPercentage.reset();
        this.forecastCompletionDate.reset();
        this.notifService.successMessage("Schedule Update posted successfully");
        this.loadForecastCompletionData();
      });
    } else {
      this.notifService.showError("Please fill all the required fields.");
    }
  }

  onEdit(index: number) {
    let activity = this.drivingActivities[index];
    this.drivingActivities.splice(index,1);
    this.drivingActivityToEdit = activity;
    this.isEditing = true;
  }

  onChange(event: any) {
    this.isEditing = event.currentTarget.checked;
  }

}
