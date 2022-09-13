import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-option-button',
  templateUrl: './option-button.component.html',
  styleUrls: ['./option-button.component.scss'],
  inputs: ['buttonText', 'route']
})
export class OptionButtonComponent implements OnInit {
  buttonText:string = ""
  route:string = "#"

  constructor() { }

  ngOnInit(): void {
  }

}
