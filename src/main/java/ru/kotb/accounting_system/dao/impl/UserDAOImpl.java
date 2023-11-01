package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;


/**
 * The implementation of the UserDAO interface.
 */
@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    @Override
    public User findByLogin(String userLogin) {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User " +
                "where login = :userLogin", User.class);
        query.setParameter("userLogin", userLogin);

        return query.uniqueResult();
    }
}
