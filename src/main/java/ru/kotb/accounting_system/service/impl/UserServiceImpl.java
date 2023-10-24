package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.GenericDAO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;


/**
 * The implementation of the UserService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl extends AbstractEntityService<User> implements UserService {

    @Autowired
    public UserServiceImpl(GenericDAO<User> genericDAOImpl) {
        super(genericDAOImpl);
        genericDAOImpl.setClass(User.class);
    }
}
