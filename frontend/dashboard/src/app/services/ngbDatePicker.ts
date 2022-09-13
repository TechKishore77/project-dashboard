import { Injectable } from "@angular/core";

@Injectable()
export class NgbDatePickerFormat {

    /**
     * getNgbDateObject(), get the Date Object for ng-bootstrap datepicker
     * @param momentDate 
     */
    getNgbDateObject(momentDate: any) {
        return momentDate ? {
            year: Number(momentDate.format('YYYY')),
            month: Number(momentDate.format('M')),
            day: Number(momentDate.format('D')),
        } : null;
    }
}