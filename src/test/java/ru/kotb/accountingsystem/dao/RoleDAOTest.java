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
import ru.kotb.accountingsystem.entity.Role;

import java.util.Optional;


@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    @Test
    public void findByNonExistedAuthorityReturnsEmptyOptional() {
        Optional<Role> roleOptional = roleDAO.findByAuthority("USER");
        Assertions.assertThat(roleOptional).isEmpty();
    }

    @Test
    public void findByExistedAuthorityReturnNonEmptyOptional() {
        Role role = new Role(Role.Authority.ADMIN);
        roleDAO.save(role);
        Optional<Role> optionalRole = roleDAO.findByAuthority("ADMIN");
        Assertions.assertThat(optionalRole).isNotEmpty();
    }
}