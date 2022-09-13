import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { HttpRestService } from '../services/http.rest.service';
import { EncryptionProvider } from '../shared/utility/encryption';
import { StoreProvider } from '../shared/utility/store';
import { AuthGuard } from './providers/auth.guard';

@NgModule({
    imports: [
        CommonModule,
    ],
    declarations: [],
    providers: [AuthService, HttpRestService],
    exports: [
        FormsModule,
        ReactiveFormsModule
    ]
})
export class CoreModule {
    static forRoot(): ModuleWithProviders<any> {
        return {
            ngModule: CoreModule,
            providers: [StoreProvider,
                AuthGuard,
                EncryptionProvider,
                HttpRestService,
            ]
        };
    }
}
