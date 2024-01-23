package ru.kotb.accountingsystem.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.configuration.TestConfig;
import ru.kotb.accountingsystem.entity.User;

import java.util.HashSet;
import java.util.Optional;


@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void findByUsernameReturnUserNotEmpty() {
        User user = new User("Test", "Test", "Test", new HashSet<>());
        userDAO.save(user);

        Optional<User> optionalUser = userDAO.findByUsername("Test");
        Assertions.assertThat(optionalUser).isNotEmpty();
    }

    @Test
    public void findByUsernameReturnUserEmpty() {
        Optional<User> optionalUser = userDAO.findByUsername("ERROR");
        Assertions.assertThat(optionalUser).isEmpty();
    }
}
