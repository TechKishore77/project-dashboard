import { NgModule } from "@angular/core";
import { StopCcpDirective } from "./stop-ccp.directive";
import { MaterialElevationDirective } from "./material-elevation.directive";
import { LazyLoadingImgDirective } from './lazy-loading-image.directive';

const coreDirectives = [
    StopCcpDirective, MaterialElevationDirective, LazyLoadingImgDirective];
@NgModule({
    declarations: [...coreDirectives],
    exports: [...coreDirectives]
})
export class CoreDirectiveModule { }
