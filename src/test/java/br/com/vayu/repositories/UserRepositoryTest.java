package br.com.vayu.repositories;

import br.com.vayu.builders.UserBuilder;
import br.com.vayu.models.User;
import br.com.vayu.models.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByEmailIncludingRole__should_return_user_by_email_including_role() {
        UserRole role = new UserRole(99, "pé na porta");

        em.persist(role);

        User user = new UserBuilder()
                .name("raul")
                .email("rar@gmail.com")
                .username("rar")
                .password("senha")
                .role(role)
                .build();

        em.persist(user);
        em.flush();
        em.clear();

        Optional<User> bdUserOptional = userRepository.findByEmailIncludingRole("rar@gmail.com");
        assertTrue(bdUserOptional.isPresent());

        User bdUser = bdUserOptional.get();

        assertEquals(user, bdUser);
    }

    @Test
    void findByEmailIncludingRole__should_return_empty_optional_when_email_non_existent() {
        Optional<User> bdUserOptional = userRepository.findByEmailIncludingRole("idontExist");

        assertFalse(bdUserOptional.isPresent());
    }

}