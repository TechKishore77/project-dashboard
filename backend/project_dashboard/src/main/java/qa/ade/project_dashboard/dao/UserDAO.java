package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.User;

public interface UserDAO {
    User findByUsername(String username);

    long createUser(User user);
}
