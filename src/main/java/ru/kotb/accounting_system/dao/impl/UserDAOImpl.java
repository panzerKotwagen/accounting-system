package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;

import java.util.List;


/**
 * The implementation of the UserDAO interface.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    
    /**
     * The SessionFactory object for working with database.
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates the query to the database and returns list of all
     * users in the table.
     *
     * @return list of all users in the table
     */
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User", User.class);
        List<User> users = query.getResultList();

        return users;
    }

    /**
     * Saves a new or update the existing user in the table.
     *
     * @param user new user object
     */
    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    /**
     * Returns the user with the specified ID.
     *
     * @param userId the ID of the user
     * @return the user object with the specified ID
     */
    @Override
    public User getUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    /**
     * Deletes the user with the specified ID.
     *
     * @param userId the ID of the user
     */
    @Override
    public void deleteUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User "
                + "where id =:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }
}
