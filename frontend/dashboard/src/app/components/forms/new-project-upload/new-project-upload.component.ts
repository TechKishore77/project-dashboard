import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UrlConstants } from 'src/app/constants/url.constants';

@Component({
  selector: 'app-new-project-upload',
  templateUrl: './new-project-upload.component.html',
  styleUrls: ['./new-project-upload.component.scss']
})
export class NewProjectUploadComponent implements OnInit {

  serverURL:string = UrlConstants.AUTH.SERVER_URL;
  @Output() success: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  onSuccess(val:boolean) {
    this.success.emit(val);
  }

}
