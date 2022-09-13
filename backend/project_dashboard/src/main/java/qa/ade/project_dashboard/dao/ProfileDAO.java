package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Profile;
import qa.ade.project_dashboard.model.User;

public interface ProfileDAO {
    Profile getUserProfile(User user);

    boolean createProfile(User user, Profile profile);

    boolean updateProfile(User user, Profile profile);
}
