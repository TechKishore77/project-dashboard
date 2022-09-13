import { CommonModule } from '@angular/common';
import { Directive, ElementRef, HostListener, Input, NgModule, forwardRef, Output, EventEmitter } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator } from '@angular/forms';
import { DomHandler } from './dom/dom-handler';
import { DEFAULT_MASKS, SAFARI_KEYS, KEYS } from './dom/key-filter.model';

export const KEYFILTER_VALIDATOR: any = {
    provide: NG_VALIDATORS,
    useExisting: forwardRef(() => KeyFilter),
    multi: true
  };
  
  @Directive({
    selector: '[keyFilter]',
    providers: [KEYFILTER_VALIDATOR]
  })
  export class KeyFilter implements Validator {
  
    @Input() pValidateOnly: boolean;
    @Input() vanish: boolean;
  
    @Output() ngModelChange: EventEmitter<any> = new EventEmitter();
  
    regex: RegExp;
  
    _pattern: any;
  
    isAndroid: boolean;
  
    lastValue: any;
  
    constructor(public el: ElementRef) {
      this.isAndroid = DomHandler.isAndroid();
    }
  
    get pattern(): any {
      return this._pattern;
    }
  
    @Input('keyFilter') set pattern(_pattern: any) {
      this._pattern = _pattern;
      this.regex = DEFAULT_MASKS[this._pattern] || this._pattern;
    }
  
    isNavKeyPress(e: KeyboardEvent) {
      let k = e.keyCode;
      k = DomHandler.getBrowser().safari ? (SAFARI_KEYS[k] || k) : k;
  
      return (k >= 33 && k <= 40) || k === KEYS.RETURN || k === KEYS.TAB || k === KEYS.ESC;
    }
  
    isSpecialKey(e: KeyboardEvent) {
      const k = e.keyCode;
      const c = e.charCode;
  
      return k === 9 || k === 13 || k === 27 || k === 16 || k === 17 || (k >= 18 && k <= 20) ||
        (DomHandler.getBrowser().opera && !e.shiftKey && (k === 8 || (k >= 33 && k <= 35) || (k >= 36 && k <= 39) || (k >= 44 && k <= 45)));
    }
  
  
    getKey(e: KeyboardEvent) {
      const k = e.keyCode || e.charCode;
      return DomHandler.getBrowser().safari ? (SAFARI_KEYS[k] || k) : k;
    }
  
    getCharCode(e: KeyboardEvent) {
      return e.charCode || e.keyCode || e.which;
    }
  
    findDelta(value: string, prevValue: string) {
      let delta = '';
  
      for (let i = 0; i < value.length; i++) {
        const str = value.substr(0, i) + value.substr(i + value.length - prevValue.length);
  
        if (str === prevValue) {
          delta = value.substr(i, value.length - prevValue.length);
        }
      }
  
      return delta;
    }
  
    isValidChar(c: string) {
      return this.regex.test(c);
    }
  
    isValidString(str: string) {
      for (let i = 0; i < str.length; i++) {
        if (!this.isValidChar(str.substr(i, 1))) {
          return false;
        }
      }
  
      return true;
    }
  
    @HostListener('input', ['$event'])
    onInput(e: KeyboardEvent) {
      if (this.isAndroid && !this.pValidateOnly) {
        let val = this.el.nativeElement.value;
        const lastVal = this.lastValue || '';
  
        const inserted = this.findDelta(val, lastVal);
        const removed = this.findDelta(lastVal, val);
        const pasted = inserted.length > 1 || (!inserted && !removed);
  
        if (pasted) {
          if (!this.isValidString(val)) {
            this.el.nativeElement.value = lastVal;
            this.ngModelChange.emit(lastVal);
          }
        } else if (!removed) {
          if (!this.isValidChar(inserted)) {
            this.el.nativeElement.value = lastVal;
            this.ngModelChange.emit(lastVal);
          }
        }
  
        val = this.el.nativeElement.value;
        if (this.isValidString(val)) {
          this.lastValue = val;
        }
      }
    }
  
    @HostListener('keypress', ['$event'])
    onKeyPress(e: KeyboardEvent) {
      if (this.isAndroid || this.pValidateOnly) {
        return;
      }
  
      const browser = DomHandler.getBrowser();
  
      if (e.ctrlKey || e.altKey) {
        return;
      }
  
      const k = this.getKey(e);
  
      if (k === 13) {
        return;
      }
  
      if (browser.mozilla && (this.isNavKeyPress(e) || k === KEYS.BACKSPACE || (k === KEYS.DELETE && e.charCode === 0))) {
        return;
      }
  
      const c = this.getCharCode(e);
      const cc = String.fromCharCode(c);
      let ok = true;
  
      if (browser.mozilla && (this.isSpecialKey(e) || !cc)) {
        return;
      }
  
      ok = this.regex.test(cc);
  
      if (!ok) {
        e.preventDefault();
      }
    }
  
    @HostListener('paste', ['$event'])
    onPaste(e) {
      const clipboardData = e.clipboardData || (<any>window).clipboardData.getData('text');
      if (clipboardData) {
        const pastedText = clipboardData.getData('text');
        for (const char of pastedText.toString()) {
          if (!this.regex.test(char)) {
            e.preventDefault();
            return;
          }
        }
      }
    }
  
    validate(c: AbstractControl): { [key: string]: any } {
      if (this.pValidateOnly) {
        const value = this.el.nativeElement.value;
        if (value && !this.regex.test(value)) {
          if (this.vanish) {
            this.el.nativeElement.value = null;
          }
          return {
            validatePattern: false
          };
        }
      }
    }
  
  }
  
  @NgModule({
    imports: [CommonModule],
    exports: [KeyFilter],
    declarations: [KeyFilter]
  })
  export class KeyFilterModule { }