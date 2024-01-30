package ru.kotb.accountingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.Role;

import java.util.Optional;


/**
 * The repository to work with the {@code Role} entity.
 */
@Repository
public interface RoleRepository extends CommonRepository<Role> {

    /**
     * Returns the role with specified name.
     *
     * @param authority the name of the role
     * @return the role with specified name
     */
    @Transactional
    Optional<Role> findByAuthority(Role.Authority authority);
}
