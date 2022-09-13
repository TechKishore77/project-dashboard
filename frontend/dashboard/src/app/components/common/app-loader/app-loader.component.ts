import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-loader',
  templateUrl: './app-loader.component.html',
  styleUrls: ['./app-loader.component.scss']
})
export class AppLoaderComponent implements OnInit {
  public toggleSpinner: boolean = false

  constructor() { }

  public start(flag: any) {
    this.toggleSpinner = flag;
  }

  public stop(flag: any) {
    this.toggleSpinner = flag;
  }

  ngOnInit() { }

}