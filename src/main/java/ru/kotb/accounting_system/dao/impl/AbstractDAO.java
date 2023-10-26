package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;


/**
 * The abstract generic DAO class used to access the tables in the
 * database "accounting_system".
 *
 * @param <E> the entity class that the DAO works with
 */
public abstract class AbstractDAO<E extends AbstractEntity> implements CommonDAO<E> {

    /**
     * The SessionFactory object for working with database.
     */
    private final SessionFactory sessionFactory;

    /**
     * The entity class.
     */
    private Class<E> eClass;

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    public AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Sets the entity class.
     *
     * @param eClass class of the entity
     */
    @Override
    public void setClass(Class<E> eClass) {
        this.eClass = eClass;
    }

    /**
     * Creates the query to the database and returns list of all
     * entities in the table.
     *
     * @return list of all entities in the table
     */
    @Override
    public List<E> getAll() {
        Session session = sessionFactory.getCurrentSession();

        Query<E> query = session.createQuery("from " + eClass.getName());

        return query.getResultList();
    }

    /**
     * Saves a new or update the existing entity in the table.
     *
     * @param entity new entity object
     */
    @Override
    public void saveOrUpdate(E entity) {
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
    public E get(int entityId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(eClass, entityId);
    }

    /**
     * Deletes the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     */
    @Override
    public void delete(int entityId) {
        Session session = sessionFactory.getCurrentSession();
        E entity = get(entityId);
        session.remove(entity);
    }
}
