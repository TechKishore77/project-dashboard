package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.model.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_USER_BY_USERNAME = "SELECT id, username, password, enabled FROM USER WHERE username = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO USER(username, password) VALUES (?,?)";


    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_USERNAME, new UserMapper(), username);
    }

    @Transactional
    public long createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);
        Object key = keyHolder.getKey();
        return (long) (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }
}
