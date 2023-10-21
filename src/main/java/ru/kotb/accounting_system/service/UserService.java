package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int userId);

    void deleteUser(int userId);
}
