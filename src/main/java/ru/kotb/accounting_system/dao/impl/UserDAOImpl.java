package ru.kotb.accounting_system.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;

import javax.persistence.EntityManager;


/**
 * The implementation of the UserDAO interface.
 */
@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the EntityManager object
     */
    @Autowired
    public UserDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }
}
