export const DEFAULT_MASKS = {
    pint: /[\d]/,
    'int': /[\d\-]/,
    pnum: /[\d\.]/,
    money: /[\d\.\s,]/,
    num: /[\d\-\.]/,
    hex: /[0-9a-f]/i,
    email: /[a-z0-9_\.\-@]/i,
    alpha: /[a-z_]/i,
    alphanum: /[a-z0-9_]/i,
    percentage: /^[1-9][0-9]^100$/
  };
  
  export const KEYS = {
    TAB: 9,
    RETURN: 13,
    ESC: 27,
    BACKSPACE: 8,
    DELETE: 46
  };
  
  export const SAFARI_KEYS = {
    63234: 37, // left
    63235: 39, // right
    63232: 38, // up
    63233: 40, // down
    63276: 33, // page up
    63277: 34, // page down
    63272: 46, // delete
    63273: 36, // home
    63275: 35  // end
  };