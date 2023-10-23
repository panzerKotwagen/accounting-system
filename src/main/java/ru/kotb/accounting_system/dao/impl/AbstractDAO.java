package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.dao.GenericDAO;

import java.util.List;


/**
 * The abstract generic DAO class used to access the tables in the
 * database "accounting_system".
 *
 * @param <T> the entity class that the DAO works with
 */
@Component
public abstract class AbstractDAO<T> implements GenericDAO<T> {

    /**
     * The SessionFactory object for working with database.
     */
    private SessionFactory sessionFactory;

    /**
     * The entity class.
     */
    private Class<T> tClass;

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Sets the entity class.
     *
     * @param tClass class of the entity
     */
    public void setClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    /**
     * Creates the query to the database and returns list of all
     * entities in the table.
     *
     * @return list of all entities in the table
     */
    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();

        Query<T> query = session.createQuery("from " + tClass.getName());

        return query.getResultList();
    }

    /**
     * Saves a new or update the existing entity in the table.
     *
     * @param entity new entity object
     */
    @Override
    public void saveOrUpdate(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity object with the specified ID
     */
    @Override
    public T get(int entityId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(tClass, entityId);
    }

    /**
     * Deletes the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     */
    @Override
    public void delete(int entityId) {
        Session session = sessionFactory.getCurrentSession();
        T entity = get(entityId);
        session.remove(entity);
    }
}
