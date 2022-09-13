package qa.ade.project_dashboard.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class User {
    private long id;
    private String username;
    private String password;
    private boolean enabled;

    public User() {
        id = 0;
        username = "";
        password = "";
        enabled = true;
    }

    public User(long id, String username, String password, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserDetails toUserDetails() {
        return new org.springframework.security.core.userdetails.User(username, password, new ArrayList<>());
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
