import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DrivingActivity } from 'src/app/model/driving-activity';
import { Project } from 'src/app/model/project';
import { DrivingActivityService } from 'src/app/services/driving-activity.service';

@Component({
  selector: 'app-driving-activity-grid',
  templateUrl: './driving-activity-grid.component.html',
  styleUrls: ['./driving-activity-grid.component.scss']
})
export class DrivingActivityGridComponent implements OnInit {
  @Input() project?: Project;
  createActivityLink:string = "#";

  activities:DrivingActivity[] = []

  constructor(private drivingActivityService: DrivingActivityService, private _changeRef: ChangeDetectorRef, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString) {
      this.createActivityLink = `/project/${numString}/schedule-update`;
    } else {
      this.createActivityLink = "#";
    }
    this.drivingActivityService.getDrivingActivities(this.project).subscribe(activities => {
      this.activities = activities;
    });
  }

  onEditActivity(editedActivity: DrivingActivity) {
    let index = this.activities.findIndex(activity => activity.id == editedActivity.id);
    this.activities[index] = editedActivity;
    this._changeRef.detectChanges();
  }

}
