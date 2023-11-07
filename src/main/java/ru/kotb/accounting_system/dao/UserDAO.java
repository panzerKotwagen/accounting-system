package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.User;

import java.util.Optional;


/**
 * The DAO interface used to access the table "users".
 */
public interface UserDAO extends CommonDAO<User> {

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    Optional<User> findByLogin(String userLogin);
}
