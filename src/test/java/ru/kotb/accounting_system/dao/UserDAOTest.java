package ru.kotb.accounting_system.dao;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.entity.User;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
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
