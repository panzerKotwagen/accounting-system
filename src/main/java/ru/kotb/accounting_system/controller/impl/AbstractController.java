package ru.kotb.accounting_system.controller.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kotb.accounting_system.controller.CommonController;
import ru.kotb.accounting_system.entity.AbstractEntity;
import ru.kotb.accounting_system.exception_handling.NoSuchEntityException;
import ru.kotb.accounting_system.service.CommonService;

import java.util.List;


/**
 * The implementation of CommonController interface.
 *
 * @param <E> the entity class
 * @param <S> the service class
 */
public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    /**
     * The service bean for working with the entities.
     */
    private final S service;

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    public AbstractController(S service) {
        this.service = service;
    }

    /**
     * Returns list of all entities in the table as JSON array.
     *
     * @return JSON array with all entities in the table
     */
    @Override
    public List<E> showAll() {
        return service.getAll();
    }

    /**
     * Returns a JSON object with description of the specified entity.
     *
     * @param entityId the specified ID of the entity
     * @return the JSON object with the specified entity
     */
    @Override
    public E get(@PathVariable("id") int entityId) {
        E entity = service.get(entityId);
        if (entity == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entityId + " in the database.");
        } else {
            return entity;
        }
    }

    /**
     * Saves the entity into the table. The request body must have
     * got the JSON object with the data of the new entity.
     *
     * @param entity the entity object
     * @return saved object
     */
    @Override
    public E add(@RequestBody E entity) {
        service.saveOrUpdate(entity);
        return entity;
    }

    /**
     * Updates the existing entity in the table. The request
     * body must have got the JSON object with the ID of an existing
     * entity and the updated data.
     *
     * @param entity entity object with specified ID
     * @return saved object
     */
    @Override
    public E update(@RequestBody E entity) {
        if (service.get(entity.getId()) == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entity.getId() + " in the database.");
        } else {
            service.saveOrUpdate(entity);
            return entity;
        }
    }

    /**
     * Deletes the entity with the specified ID and return the
     * informative message.
     *
     * @param entityId specified ID of the entity
     * @return operation status message
     */
    @Override
    public String delete(@PathVariable("id") int entityId) {
        E entity = service.get(entityId);
        if (entity == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entityId + " in the database.");
        } else {
            service.delete(entityId);
            return "Entity with ID = " + entityId + " was deleted.";
        }
    }
}
