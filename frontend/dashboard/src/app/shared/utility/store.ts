import { Injectable } from '@angular/core';
import { EncryptionProvider } from './encryption';

/*
 *  Common Utilities provider
 *  @author Kishore K. Konangi
 */
@Injectable()
export class StoreProvider {

  constructor(private encrypt: EncryptionProvider) {

  }

  /**
 * Set lang attr & class to HTML tag
 * @param lang
 */
  setHtmlLang(lang: string) {
    let html = document.getElementsByTagName('html')[0];
    html.setAttribute('lang', lang);
    let direction = (lang === 'ar' ? 'RTL' : 'LTR');
    html.setAttribute('dir', direction);
    html.classList ? html.classList.add(lang) : html.className += ' ' + lang;
  }

  /**
   * Store information to local storage
   * @param key
   * @param value
   */
  setStoreItem(key: string, value: any) {
    sessionStorage.setItem(key, this.encrypt.encryptData(value));
  }

  /**
   * get information from localStorage
   * @param key
   */
  getStoreItem(key: any) {
    if (sessionStorage.getItem(key)) {
      return this.encrypt.decryptData(sessionStorage.getItem(key));
    }
    return '';
  }

  /**
   * Remove store item
   * @param key
   */
  removeStoreItem(key: any) {
    sessionStorage.removeItem(key);
  }

  /**
   * Remove All session store items
   */
  removeAllItems() {
    sessionStorage.clear();
  }
}
