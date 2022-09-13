package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.model.UserRole;
import qa.ade.project_dashboard.model.mapper.UserRoleMapper;

import java.util.List;


@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("userRoleDAO")
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ROLES_FOR_USER = "SELECT UR.role, R.name FROM USER_ROLE AS UR INNER JOIN ROLE R ON UR.role = R.id WHERE UR.username = ?";

    public List<UserRole> getRoles(User user) {
        return jdbcTemplate.query(SQL_GET_ROLES_FOR_USER, new UserRoleMapper());
    }

    public boolean setRoles(List<UserRole> roles) {
        return false;
    }
}
