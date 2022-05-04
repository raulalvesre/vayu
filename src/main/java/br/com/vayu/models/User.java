package br.com.vayu.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "role_id", nullable = false)
    private UserRole role;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
               active == user.active &&
               role.equals(user.role) &&
               name.equals(user.name) &&
               email.equals(user.email) &&
               username.equals(user.username) &&
               password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, name, email, username, password, active);
    }

}

