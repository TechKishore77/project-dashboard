package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<Profile> {
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setName(rs.getString("name"));
        profile.setUsername(rs.getString("username"));
        return profile;
    }
}
