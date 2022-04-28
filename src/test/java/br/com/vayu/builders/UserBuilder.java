package br.com.vayu.builders;

import br.com.vayu.models.User;
import br.com.vayu.models.UserRole;

public class UserBuilder {

    private String name;
    private String email;
    private String username;
    private String password;
    private UserRole role;

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(UserRole role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(name, email, username, password, role);
    }

}
