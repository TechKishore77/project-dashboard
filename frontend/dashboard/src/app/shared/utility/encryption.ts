import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { ApplicationConstants } from 'src/app/constants/application.constants';

/*
 *  Common Utilities provider
 *  @author Kishore K. Konangi
 */
@Injectable()
export class EncryptionProvider {

  private key = CryptoJS.enc.Utf8.parse(APP_KEY);

  constructor() {

  }
  // ---------------------------------------------- Encryption ----------------------------------------------------------

  encryptData(data: any) {
    return CryptoJS.AES.encrypt(data, APP_KEY).toString();
  }

  decryptData(data: any) {
    return CryptoJS.AES.decrypt(data, APP_KEY).toString(CryptoJS.enc.Utf8);
  }

  // --------------------------------------------------------------------------------------------------------------------
}

export const APP_KEY = ApplicationConstants.ENCRYPT_KEY;
