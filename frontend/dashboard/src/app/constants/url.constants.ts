import { environment } from "src/environments/environment";

/**
 * Contains all service URL's for Prepaid card application
 * @returns URL String
 */
export const UrlConstants = {

  BASE_URL: environment.baseUrl,
  AUTH: {
    LOGIN: '/auth/token',
    LOGOUT: '/auth/logout',
    SERVER_URL: environment.baseUrl
  },
  USER: {
    REGISTER: '/auth/register'
  }
};
