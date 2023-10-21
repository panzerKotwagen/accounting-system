package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.User;

import java.util.List;


public interface UserDAO {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int userId);

    void deleteUser(int userId);
}
