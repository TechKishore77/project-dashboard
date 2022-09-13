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
        SERVER_URL: 'http://adebackend-env.eba-ugdyzma3.us-east-1.elasticbeanstalk.com'
        // SERVER_URL: 'http://localhost:8080'
    },
    USER: {
      REGISTER: '/auth/register'
    }
};
