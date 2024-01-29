package ru.kotb.accountingsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.Role;

import java.util.Optional;


/**
 * The repository to work with the {@code Role} entity.
 */
@Repository
public interface RoleDAO extends CommonRepository<Role> {

    /**
     * Returns the role with specified name.
     *
     * @param authority the name of the role
     * @return the role with specified name
     */
    @Transactional
    @Query(value = "SELECT * FROM ROLES WHERE AUTHORITY = ?1", nativeQuery = true)
    Optional<Role> findByAuthority(String authority);
}
