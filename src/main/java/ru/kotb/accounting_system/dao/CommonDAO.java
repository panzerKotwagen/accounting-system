package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;


/**
 * The generic DAO interface used to access the entities in the
 * database "accounting_system".
 */
public interface CommonDAO<E extends AbstractEntity> {

    /**
     * Sets the entity.
     * @param tClass class of the entity
     */
    void setClass(Class<E> tClass);
    
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
    void saveOrUpdate(E entity);

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
