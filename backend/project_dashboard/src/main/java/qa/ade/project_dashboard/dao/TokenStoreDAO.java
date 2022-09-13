package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.JwtTokenPair;

import java.sql.Timestamp;

public interface TokenStoreDAO {
    boolean addToken(String token, String username, Timestamp expiry);

    boolean isTokenValid(String token, String username);

    boolean expireToken(String token);

    boolean addTokenPair(JwtTokenPair tokenPair, String username, Timestamp accessExpiry, Timestamp refreshExpiry);
}
