package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Profile;
import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.model.mapper.ProfileMapper;

import java.sql.Types;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("profileDAO")
public class ProfileDAOImpl implements ProfileDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_USER_PROFILE = "SELECT * FROM PROFILE WHERE username = ?";
    private static final String SQL_CREATE_PROFILE = "INSERT INTO PROFILE(name, username) VALUES(?,?)";
    private static final String SQL_UPDATE_PROFILE = "UPDATE PROFILE SET name = ?,SET username = ? WHERE id = ?";

    public Profile getUserProfile(User user) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_PROFILE, new ProfileMapper(), user.getUsername());
    }

    @Transactional
    public boolean createProfile(User user, Profile profile) {
        return jdbcTemplate.update(SQL_CREATE_PROFILE,
                new Object[]{
                        profile.getName(),
                        profile.getUsername(),
                },
                new int[]{
                        Types.VARCHAR,
                        Types.VARCHAR,
                }
        ) > 0;
    }

    @Transactional
    public boolean updateProfile(User user, Profile profile) {
        return jdbcTemplate.update(SQL_UPDATE_PROFILE,
                new Object[]{
                        profile.getName(),
                        profile.getUsername(),
                        profile.getId()
                },
                new int[]{
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.INTEGER
                }
        ) > 0;
    }
}
