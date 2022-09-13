import { ChangeDetectorRef, Component } from '@angular/core';
import { RouteConfigLoadEnd, RouteConfigLoadStart, Router } from '@angular/router';
import { HTTPStatus } from './shared/interceptors/loader.interceptor';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {

  toggleLoader!: boolean;
  loadingRouteConfig!: boolean;
  router!:Router;

  constructor(private _loaderStatus: HTTPStatus, private _router: Router, private _changeRef: ChangeDetectorRef) {
    // Below call determines to show/hide Loader when ever an HTTP call triggers via Interceptor
    this._loaderStatus.getHttpStatus().subscribe((status: boolean) => { this.toggleLoader = status; });
    this.router = _router;
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this._router.events.subscribe(event => {
      if (event instanceof RouteConfigLoadStart) {
        this.loadingRouteConfig = true;
      } else if (event instanceof RouteConfigLoadEnd) {
        this.loadingRouteConfig = false;
      }
    });
    this._changeRef.detectChanges();
  }
}
