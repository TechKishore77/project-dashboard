package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.ProfileDAO;
import qa.ade.project_dashboard.dao.UserDAO;
import qa.ade.project_dashboard.dao.UserRoleDAO;
import qa.ade.project_dashboard.model.Profile;
import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.model.UserRole;

import java.util.List;

@Service("userService")
@ComponentScan("qa.ade.project_dashboard")
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ProfileDAO profileDAO;

    @Autowired
    UserRoleDAO userRoleDAO;

    public long createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("Hashed password: " + user.getPassword());
        long newUserId = userDAO.createUser(user);
        if (newUserId > 0) {
            Profile profile = new Profile(0, user.getUsername(), "");
            profileDAO.createProfile(user, profile);
        }
        return newUserId;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public Profile getUserProfile(User user) {
        return profileDAO.getUserProfile(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User with given username not found");
        return user.toUserDetails();
    }

    public List<UserRole> getRoles(User user) {
        return userRoleDAO.getRoles(user);
    }
}
