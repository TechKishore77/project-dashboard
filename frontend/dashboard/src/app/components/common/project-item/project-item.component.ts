import { Component, Input, OnInit } from '@angular/core';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styleUrls: ['./project-item.component.scss'],
  inputs : ['project']
})
export class ProjectItemComponent implements OnInit {
  project?: Project;
  @Input() viewRoute: string = "#"

  constructor() {}

  ngOnInit(): void {
  }


}
