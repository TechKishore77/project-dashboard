import { NgbDateParserFormatter, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Injectable } from '@angular/core';

@Injectable()
export class NgbDatepickerFormat extends NgbDateParserFormatter {
    parse(value: string): NgbDateStruct {
        if (value) {
            const dateParts: any = value.trim().split('-');
            if (dateParts.length === 1 && Number.isInteger(dateParts[0])) {
                return { day: Number(dateParts[0]), month: 0, year: 0 };
            } else if (dateParts.length === 2 && Number.isInteger(dateParts[0]) && Number.isInteger(dateParts[1])) {
                return { day: Number(dateParts[0]), month: Number(dateParts[1] - 1), year: 0 };
            } else if (dateParts.length === 3 && Number.isInteger(dateParts[0]) && Number.isInteger(dateParts[1] - 1) && Number.isInteger(dateParts[2])) {
                return { day: Number(dateParts[0]), month: Number(dateParts[1] - 1), year: Number(dateParts[2]) };
            }
        }
        let date = new Date();
        return { day: date.getUTCDay(), month: (date.getUTCMonth()) + 1, year: date.getUTCFullYear() };
    }

    /**
     * format(), parse to QIB Date format (DD/MM/YYYY).
     */
    format(date: NgbDateStruct): string {
        return date ?
            `${Number.isInteger(date.day) ? date.day : ''}/${Number.isInteger(date.month) ? date.month : ''}/${date.year}` :
            '';
    }
}