import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, TemplateRef } from '@angular/core';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Issue } from 'src/app/model/issue';
import { IssueService } from 'src/app/services/issue.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-issue-grid-item',
  templateUrl: './issue-grid-item.component.html',
  styleUrls: ['./issue-grid-item.component.scss']
})
export class IssueGridItemComponent implements OnInit, OnChanges {

  @Input() issue?:Issue
  @Output() editIssue = new EventEmitter<Issue>();
  @Output() deleteIssue = new EventEmitter<Issue>();
  modalRef:any;
  config = {
    backdrop: true
  };
  faTrash=faTrash
  faEdit=faEdit

  constructor(private modalService: NgbModal, private _notifService: NotificationService, private _issueService: IssueService) { }
  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {
  }

  openIssueEditModal(template: TemplateRef<any>) {
    this.modalService.open(template, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
    });
    return false;
  }

  issueEdited(issue: Issue) {
    this.issue = issue;
    this.editIssue.emit(issue);
    this.modalService.dismissAll();
  }

  editFailed() {
    this._notifService.showError("Failed to update issue. Please try again.");
    this.modalService.dismissAll();
  }

  onDeleteButton(confirmDg: TemplateRef<any>) {
    this.modalService.open(confirmDg, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      console.log(`${result} clicked`);
      if(result == 'Yes') {
        this._issueService.deleteIssue(this.issue).subscribe((res) => {
          this.deleteIssue.emit(this.issue);
          this._notifService.successMessage("Issue deleted successfully");
        }, (err)=> {
          this._notifService.showError("Failed to delete issue. Please try again later.");
        });
      }
    });
  }

}
