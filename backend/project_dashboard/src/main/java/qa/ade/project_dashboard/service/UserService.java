package qa.ade.project_dashboard.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import qa.ade.project_dashboard.model.Profile;
import qa.ade.project_dashboard.model.User;

public interface UserService extends UserDetailsService {
    long createUser(User user);

    User findByUsername(String username);

    Profile getUserProfile(User user);
}
