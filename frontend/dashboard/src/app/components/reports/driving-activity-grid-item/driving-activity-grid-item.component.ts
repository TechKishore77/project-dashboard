import { Component, EventEmitter, Input, OnInit, Output, TemplateRef } from '@angular/core';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DrivingActivity } from 'src/app/model/driving-activity';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-driving-activity-grid-item',
  templateUrl: './driving-activity-grid-item.component.html',
  styleUrls: ['./driving-activity-grid-item.component.scss']
})
export class DrivingActivityGridItemComponent implements OnInit {
  @Input() activity?:DrivingActivity;
  @Output() editActivity:EventEmitter<DrivingActivity> = new EventEmitter<DrivingActivity>();
  config = {
    backdrop: true
  };
  faEdit=faEdit;

  constructor(private modalService: NgbModal, private _notifService: NotificationService) { }

  ngOnInit(): void {
  }

  openDrivingActivityEditModal(template: TemplateRef<any>) {
    this.modalService.open(template, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
    });
    return false;
  }

  onEditActivity(activity:DrivingActivity) {
    this.activity = activity;
    this.editActivity.emit(activity);
    this.modalService.dismissAll();
  }

  onEditFailed() {
    this._notifService.showError("Failed to update driving activity. Please try again later.");
    this.modalService.dismissAll();
  }

}
