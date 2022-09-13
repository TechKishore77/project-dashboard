import { CommonModule, TitleCasePipe } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UserService } from "../core/services/user.service";
import { NotificationService } from "../services/notification.service";
import { CoreDirectiveModule } from './directives/core.directive.module';
import { MaterialModule } from "./material/material.module";
import { EncryptionProvider } from './utility/encryption';
import { StoreProvider } from './utility/store';


@NgModule({
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        CoreDirectiveModule,
        MaterialModule
    ],
    declarations: [],
    providers: [StoreProvider, EncryptionProvider, TitleCasePipe, NotificationService, UserService],
    entryComponents: []
})
export class SharedModule { }
