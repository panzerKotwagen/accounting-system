package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;


/**
 * The generic DAO interface that describes standard CRUD operations.
 *
 * @param <E> the entity class that the DAO works with
 */
public interface ICommonDAO<E extends AbstractEntity> {

    /**
     * Sets the entity.
     *
     * @param eClass class of the entity
     */
    void setClass(Class<E> eClass);

    /**
     * Returns list of all entities in the table.
     *
     * @return list of all entities in the table.
     */
    List<E> getAll();

    /**
     * Adds a new entity to the table or update the existing.
     *
     * @param entity new entity
     */
    E saveOrUpdate(E entity);

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity with the specified ID
     */
    E get(int entityId);

    /**
     * Deletes the entity with the specified ID in the table.
     *
     * @param entityId the ID of the entity
     */
    void delete(int entityId);
}
