package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.entity.User;

import java.util.List;


/**
 * The class that provides working with the users.
 */
public interface UserService {

    /**
     * Returns list of all users in the table.
     *
     * @return list of all users in the table
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
