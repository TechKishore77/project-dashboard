import { Component, OnInit, TemplateRef } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'],
  host: { '[class.ngb-toasts]': 'true' }
})
export class NotificationComponent implements OnInit {

  constructor(public toastService: NotificationService) { }

  ngOnInit(): void {
  }

  isTemplate(toast: any) { return toast.textOrTpl instanceof TemplateRef; }

}
