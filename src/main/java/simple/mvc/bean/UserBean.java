package simple.mvc.bean;

import java.util.List;

public class UserBean {

    private String username;
    private String password;
    private List<String> roles;
    private boolean enabled;

    public String getUsername() {
        return username;
    }
    public UserBean setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public UserBean setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public List<String> getRoles() {
        return roles;
    }
    public UserBean setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public UserBean setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
