package ru.kotb.accountingsystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.Role;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRep;

    @Test
    public void findByNonExistedAuthorityReturnsEmptyOptional() {
        Optional<Role> roleOptional = roleRep.findByAuthority("USER");
        Assertions.assertThat(roleOptional).isEmpty();
    }

    @Test
    public void findByExistedAuthorityReturnNonEmptyOptional() {
        Role role = new Role(Role.Authority.ADMIN);
        roleRep.save(role);
        Optional<Role> optionalRole = roleRep.findByAuthority("ADMIN");
        Assertions.assertThat(optionalRole).isNotEmpty();
    }
}