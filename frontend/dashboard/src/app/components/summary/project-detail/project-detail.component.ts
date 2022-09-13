import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Project } from '../../../model/project';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss'],
  inputs : ['project']
})
export class ProjectDetailComponent implements OnInit, OnChanges {
  project?: Project;

  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {
  }

}
