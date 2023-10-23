package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.GenericDAO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;

import java.util.List;


/**
 * The implementation of the UserService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {

    /**
     * The DAO object for getting access to the "users" table.
     */
    private final GenericDAO<User> userDAO;

    @Autowired
    public UserServiceImpl(GenericDAO<User> genericDAOImpl) {
        this.userDAO = genericDAOImpl;
        this.userDAO.setClass(User.class);
    }

    /**
     * Returns list of all users in the table.
     *
     * @return list of all users in the table
     */
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    /**
     * Adds new user to the table.
     *
     * @param user new user
     */
    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveOrUpdate(user);
    }

    /**
     * Returns the user with the specified ID.
     *
     * @param userId the ID of the user
     * @return the user with the specified ID
     */
    @Override
    @Transactional
    public User getUser(int userId) {
        return userDAO.get(userId);
    }

    /**
     * Deletes the user with the specified ID in the table.
     *
     * @param userId the ID of the user
     */
    @Override
    @Transactional
    public void deleteUser(int userId) {
        userDAO.delete(userId);
    }
}
