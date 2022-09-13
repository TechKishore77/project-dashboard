import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { DrivingActivity } from 'src/app/model/driving-activity';


@Component({
  selector: 'app-driving-activity-table',
  templateUrl: './driving-activity-table.component.html',
  styleUrls: ['./driving-activity-table.component.scss'],
  inputs: ['activities']
})
export class DrivingActivityTableComponent implements OnInit {

  activities?:DrivingActivity[]
  @Output() activitiesChange = new EventEmitter<DrivingActivity[]>();
  @Output() editRequest = new EventEmitter<number>();
  faTrash=faTrash
  faEdit=faEdit

  constructor() { }

  ngOnInit(): void {
  }


  onDelete(index:number) {
    this.activities?.splice(index,1);
  }

  onEdit(index:number) {
    this.editRequest.emit(index);
  }

}
