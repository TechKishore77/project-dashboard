import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Issue } from 'src/app/model/issue';
import { Project } from 'src/app/model/project';
import { IssueService } from 'src/app/services/issue.service';

@Component({
  selector: 'app-issues-grid',
  templateUrl: './issues-grid.component.html',
  styleUrls: ['./issues-grid.component.scss']
})
export class IssuesGridComponent implements OnInit {
  @Input() project?:Project

  issues:Issue[] = []
  createIssueLink:string = "#"

  constructor(private issueService: IssueService, private _changeRef: ChangeDetectorRef, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString) {
      this.createIssueLink = `/project/${numString}/issue-form`;
    } else {
      this.createIssueLink = "#";
    }
    this.issueService.getIssues(this.project).subscribe(issues => {
      this.issues = issues;
    });
  }

  onEditIssue(editedIssue: Issue) {
    let index = this.issues.findIndex(issue => issue.id == editedIssue.id);
    this.issues[index] = editedIssue;
    this._changeRef.detectChanges();
  }

  onDeleteIssue(deletedIssue: Issue) {
    let index = this.issues.findIndex(issue => issue.id == deletedIssue.id);
    this.issues.splice(index, 1);
    this._changeRef.detectChanges();
  }

}
