package br.com.vayu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class UserRole {

    @Id
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Deprecated
    public UserRole() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id && name.equals(userRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
