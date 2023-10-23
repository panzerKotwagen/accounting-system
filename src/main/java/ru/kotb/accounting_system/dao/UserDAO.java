package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.User;

import java.util.List;


/**
 * The DAO class used to access the database "users".
 */
public interface UserDAO {

    /**
     * Returns list of all users in the table.
     *
     * @return list of all users in the table.
     */
    List<User> getAllUsers();

    /**
     * Adds new user to the table.
     *
     * @param user new user
     */
    void saveUser(User user);

    /**
     * Returns the user with the specified ID.
     *
     * @param userId the ID of the user
     * @return the user with the specified ID
     */
    User getUser(int userId);

    /**
     * Deletes the user with the specified ID in the table.
     *
     * @param userId the ID of the user
     */
    void deleteUser(int userId);
}
