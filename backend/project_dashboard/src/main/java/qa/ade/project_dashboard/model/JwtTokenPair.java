package qa.ade.project_dashboard.model;

import java.io.Serializable;

public class JwtTokenPair implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String accessToken;
    private final String refreshToken;

    public JwtTokenPair(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
