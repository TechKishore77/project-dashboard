import { Injectable, TemplateRef } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    toasts: any[] = [];

    // Push new notifications to array with content and options
    show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
        this.toasts.push({ textOrTpl, ...options });
    }

    // Callback method to remove notification DOM element from view
    remove(toast: any) {
        this.toasts = this.toasts.filter(t => t !== toast);
    }

    successMessage(message: string) {
        this.show(message, {
            classname: 'bg-success text-light',
            delay: 5000,
            autohide: true,
            // headertext: 'Notification Header'
        });
    }

    showError(error: string) {
        this.show(error, {
            classname: 'bg-danger text-light',
            delay: 5000,
            autohide: true,
            // headertext: 'Error!!!'
        });
    }

}