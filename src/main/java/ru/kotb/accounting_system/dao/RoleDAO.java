package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.Role;

import java.util.Optional;


/**
 * The DAO interface used to access the table "roles".
 */
public interface RoleDAO extends CommonDAO<Role> {

    /**
     * Returns the role with specified name.
     *
     * @param authority the name of the role
     * @return the role with specified name
     */
    Optional<Role> findByAuthority(String authority);
}
