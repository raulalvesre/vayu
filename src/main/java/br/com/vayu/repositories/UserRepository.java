package br.com.vayu.repositories;

import br.com.vayu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            SELECT user
            FROM User user
            JOIN FETCH user.role
            WHERE user.email = :email""")
    Optional<User> findByEmailIncludingRole(@Param("email") String email);

}
