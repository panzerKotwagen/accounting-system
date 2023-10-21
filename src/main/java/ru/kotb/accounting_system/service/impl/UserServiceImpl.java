package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.impl.UserDAOImpl;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;

import java.util.List;


@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {


    private UserDAOImpl userDAO;

    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int empId) {
        return userDAO.getUser(empId);
    }

    @Override
    @Transactional
    public void deleteUser(int empId) {
        userDAO.deleteUser(empId);
    }
}
