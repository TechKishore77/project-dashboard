import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { ApplicationConstants } from 'src/app/constants/application.constants';
import { StoreProvider } from 'src/app/shared/utility/store';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private store: StoreProvider,
        private _router: Router) {
    }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        if (this.store.getStoreItem(ApplicationConstants.ACCESS_TOKEN_KEY)) {
            return true;
        } else {
            this._router.navigate(['/login']);
            return false;
        }
    }
}
