package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;

import javax.persistence.EntityManager;
import java.util.Optional;


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
    public UserDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    @Override
    public Optional<User> findByLogin(String userLogin) {
        Session session = sessionFactory.unwrap(Session.class);

        Query<User> query = session.createQuery("from User " +
                "where login = :userLogin", User.class);
        query.setParameter("userLogin", userLogin);

        return Optional.ofNullable(query.uniqueResult());
    }
}
