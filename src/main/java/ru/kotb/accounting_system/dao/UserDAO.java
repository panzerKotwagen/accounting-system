package ru.kotb.accounting_system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;


/**
 * The implementation of the UserDAO interface.
 */
@Repository
public class UserDAO extends AbstractDAO<User> {

    /**
     * Creates the component and binds it with the entityManager
     * object.
     *
     * @param entityManager the entityManager object
     */
    @Autowired
    public UserDAO(EntityManager entityManager) {
        super(entityManager);
        this.setClass(User.class);
    }

    /**
     * Returns the user with the specified login.
     *
     * @param userLogin user login
     * @return the user with the specified login or null
     */
    public Optional<User> findByUsername(String userLogin) {
        Query query = entityManager.createQuery("from User " +
                "where username = :userLogin", User.class);
        query.setParameter("userLogin", userLogin);

        return query.getResultList().stream().findFirst();
    }
}
