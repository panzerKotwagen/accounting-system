package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.IUserDAO;
import ru.kotb.accounting_system.entity.User;

import javax.persistence.EntityManager;
import java.util.Optional;


/**
 * The implementation of the UserDAO interface.
 */
@Repository
//TODO: replace using Hibernate with JPA
public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    /**
     * Creates the component and binds it with the entityManager
     * object.
     *
     * @param entityManager the entityManager object
     */
    @Autowired
    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    @Override
    public Optional<User> findByUsername(String userLogin) {
        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User " +
                "where username = :userLogin", User.class);
        query.setParameter("userLogin", userLogin);

        return Optional.ofNullable(query.uniqueResult());
    }
}
