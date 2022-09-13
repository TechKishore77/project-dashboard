import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faChartBar, faDownload, faPowerOff, faProjectDiagram, faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserService } from 'src/app/core/services/user.service';
import { NotificationService } from 'src/app/services/notification.service';
import { StoreProvider } from 'src/app/shared/utility/store';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public faPowerOff = faPowerOff;
  public faUser = faUser;
  public faChartBar = faChartBar;
  public faProjectDiagram = faProjectDiagram;
  public faDownload = faDownload;
  public loggedInUser = "";

  constructor(private _userService: UserService, private _store: StoreProvider, public router: Router, private _notifyService: NotificationService, private _authService: AuthService) { }

  ngOnInit(): void {
    this.loggedInUser = this._authService.getLoggedInUsername();
  }

  logoff() {
    this._userService.logoutUser().subscribe(() => {
      //Remove all items from sessionStorage and redirect to login page
      this._store.removeAllItems();
      this._notifyService.successMessage("Logged off succesfully.!!");
      this.router.navigate(['/login']);
    });
  }

}
