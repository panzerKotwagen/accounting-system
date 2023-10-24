package ru.kotb.accounting_system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.GenericDAO;
import ru.kotb.accounting_system.service.GenericEntityService;

import java.util.List;


/**
 *
 * @param <T>
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public abstract class AbstractEntityService<T> implements GenericEntityService<T> {

    /**
     * The DAO object for getting access to the specified table.
     */
    private final GenericDAO<T> entityDAO;

    /**
     * Links the specified DAO with the service.
     * @param genericDAOImpl the DAO of the specified entity class
     */
    public AbstractEntityService(GenericDAO<T> genericDAOImpl) {
        this.entityDAO = genericDAOImpl;
    }

    /**
     * Returns list of all entities in the table.
     *
     * @return list of all entities in the table
     */
    @Override
    @Transactional
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    /**
     * Adds new entity to the table.
     *
     * @param entity new entity
     */
    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        entityDAO.saveOrUpdate(entity);
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity with the specified ID
     */
    @Override
    @Transactional
    public T get(int entityId) {
        return entityDAO.get(entityId);
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
