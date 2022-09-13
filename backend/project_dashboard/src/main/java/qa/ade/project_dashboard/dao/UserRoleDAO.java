package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.model.UserRole;

import java.util.List;

public interface UserRoleDAO {
    List<UserRole> getRoles(User user);

    boolean setRoles(List<UserRole> roles);
}
