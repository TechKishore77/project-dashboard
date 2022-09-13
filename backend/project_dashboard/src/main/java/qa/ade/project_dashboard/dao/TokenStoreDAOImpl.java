package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.JwtTokenPair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("tokenStoreDAO")
public class TokenStoreDAOImpl implements TokenStoreDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_TOKEN = "INSERT INTO TOKEN_STORE(token, username, expiry_time, valid) VALUES (?,?,?, TRUE)";
    private static final String SQL_EXPIRE_TOKEN = "UPDATE TOKEN_STORE SET valid = FALSE WHERE token = ?";
    private static final String SQL_CHECK_TOKEN_VALID = "SELECT valid FROM TOKEN_STORE WHERE token = ? AND TO_SECONDS(expiry_time) - TO_SECONDS(CURRENT_TIMESTAMP)  > 0";

    @Transactional
    public boolean addToken(String token, String username, Timestamp expiry) {
        return jdbcTemplate.update(SQL_INSERT_TOKEN,
                new Object[]{
                        token,
                        username,
                        expiry
                },
                new int[]{
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.TIMESTAMP
                }
        ) > 0;
    }

    public boolean isTokenValid(String token, String username) {
        try {
            Object obj = jdbcTemplate.queryForObject(SQL_CHECK_TOKEN_VALID, new RowMapper<Boolean>() {
                public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getBoolean("valid");
                }
            }, token);
            if (obj != null) return (boolean) obj;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean expireToken(String token) {
        return jdbcTemplate.update(SQL_EXPIRE_TOKEN, new Object[]{token}, new int[]{Types.VARCHAR}) > 0;
    }

    @Transactional
    public boolean addTokenPair(JwtTokenPair tokenPair, String username, Timestamp accessExpiry, Timestamp refreshExpiry) {
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP};
        List<Object[]> batchArgs = new ArrayList<Object[]>();

        batchArgs.add(new Object[]{tokenPair.getAccessToken(), username, accessExpiry});
        batchArgs.add(new Object[]{tokenPair.getRefreshToken(), username, refreshExpiry});

        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_TOKEN, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }

    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void purgeOldTokens() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        jdbcTemplate.update("DELETE from TOKEN_STORE WHERE expiry_time <? OR valid = FALSE", new Object[]{now}, new int[]{Types.TIMESTAMP});
    }
}
