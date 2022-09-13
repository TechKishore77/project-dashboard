package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.TokenStoreDAO;
import qa.ade.project_dashboard.model.JwtTokenPair;

import java.sql.Timestamp;

@ComponentScan("qa.ade.project_dashboard")
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenStoreDAO tokenStoreDAO;

    public boolean addToken(String token, String username, Timestamp expiry) {
        return tokenStoreDAO.addToken(token, username, expiry);
    }

    public boolean isTokenValid(String token, String username) {
        return tokenStoreDAO.isTokenValid(token, username);
    }

    public boolean expireToken(String token) {
        return tokenStoreDAO.expireToken(token);
    }

    public boolean addTokenPair(JwtTokenPair tokenPair, String username, Timestamp accessExpiry, Timestamp refreshExpiry) {
        return tokenStoreDAO.addTokenPair(tokenPair, username, accessExpiry, refreshExpiry);
    }

}
