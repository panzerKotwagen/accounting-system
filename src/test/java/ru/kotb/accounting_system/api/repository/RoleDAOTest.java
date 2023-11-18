package ru.kotb.accounting_system.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.entity.Role;

import java.util.Optional;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    @Test
    public void findByNonExistedAuthorityReturnsEmptyOptional() {
        Optional<Role> roleOptional = roleDAO.findByAuthority("USER_1");
        Assertions.assertThat(roleOptional).isEmpty();
    }

    @Test
    public void findByExistedAuthorityReturnNonEmptyOptional() {
        Role role = new Role("ADMIN");
        roleDAO.save(role);
        Optional<Role> optionalRole = roleDAO.findByAuthority("ADMIN");
        Assertions.assertThat(optionalRole).isNotEmpty();
    }
}