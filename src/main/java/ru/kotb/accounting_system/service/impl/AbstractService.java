package ru.kotb.accounting_system.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.AbstractDAO;
import ru.kotb.accounting_system.entity.AbstractEntity;
import ru.kotb.accounting_system.service.CommonService;

import java.util.List;


/**
 * The abstract service provides following operations with an entity:
 * getting all records, getting a certain object by ID, adding a new
 * one and deleting it by ID. Each class that extends it must specify
 * the entity type in the parameter and set the entity class via
 * setter in the constructor.
 *
 * @param <E> the class of the entity
 * @param <D> the class of the DAO
 */
public abstract class AbstractService<E extends AbstractEntity,
        D extends AbstractDAO<E>> implements CommonService<E> {

    /**
     * The DAO object for getting access to the specified table.
     */
    protected final D entityDAO;

    /**
     * Links the specified DAO with the service.
     *
     * @param genericDAOImpl the DAO of the specified entity class
     */
    public AbstractService(D genericDAOImpl) {
        this.entityDAO = genericDAOImpl;
    }

    /**
     * Returns list of all entities in the table.
     *
     * @return list of all entities in the table
     */
    @Override
    @Transactional
    public List<E> getAll() {
        return entityDAO.findAll();
    }

    /**
     * Adds new entity to the table.
     *
     * @param entity new entity
     */
    @Override
    @Transactional
    public E saveOrUpdate(E entity) {
        return entityDAO.save(entity);
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity with the specified ID
     */
    @Override
    @Transactional
    public E get(int entityId) {
        return entityDAO.findById(entityId).get();
    }

    /**
     * Deletes the entity with the specified ID in the table.
     *
     * @param entityId the ID of the entity
     */
    @Override
    @Transactional
    public void delete(int entityId) {
        entityDAO.delete(entityId);
    }
}
