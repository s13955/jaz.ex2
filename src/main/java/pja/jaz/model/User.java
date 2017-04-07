package pja.jaz.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 478519426915421119L;

    private String username;
    private String password;
    private String email;
    private Role role = Role.USER;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isPremium() {
        return Role.ADMIN == role || Role.PREMIUM == role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("User{username='%s', password='%s', role=%s}",
                username, password, role);
    }
}
