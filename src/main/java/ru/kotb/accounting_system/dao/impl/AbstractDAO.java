package ru.kotb.accounting_system.dao.impl;

import ru.kotb.accounting_system.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * The abstract DAO class provides standard CRUD operation witn an
 * entity.
 *
 * @param <E> the entity class that the DAO works with
 */
public abstract class AbstractDAO<E extends AbstractEntity> {

    /**
     * The EntityManager object for working with database.
     */
    protected final EntityManager entityManager;

    /**
     * The entity class.
     */
    private Class<E> eClass;

    /**
     * Creates the component and binds it with the entityManager
     * object.
     *
     * @param entityManager the EntityManager object
     */
    public AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Sets the entity class.
     *
     * @param eClass class of the entity
     */

    public void setClass(Class<E> eClass) {
        this.eClass = eClass;
    }

    /**
     * Creates the query to the database and returns list of all
     * entities in the table.
     *
     * @return list of all entities in the table
     */
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Query query = entityManager.createQuery("from " + eClass.getName());
        return query.getResultList();
    }

    /**
     * Saves a new or update the existing entity in the table.
     *
     * @param entity new entity object
     */

    public E saveOrUpdate(E entity) {
        return entityManager.merge(entity);
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity object with the specified ID
     */

    public E get(int entityId) {
        return entityManager.find(eClass, entityId);
    }

    /**
     * Deletes the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     */

    public void delete(int entityId) {
        entityManager.remove(get(entityId));
    }
}
