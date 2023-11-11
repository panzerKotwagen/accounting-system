package ru.kotb.accounting_system.controller.impl;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kotb.accounting_system.controller.CommonController;
import ru.kotb.accounting_system.entity.AbstractEntity;
import ru.kotb.accounting_system.exception_handling.NoSuchEntityException;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.CommonService;


/**
 * The implementation of CommonController interface.
 *
 * @param <E> the entity class
 * @param <S> the service class
 */
public abstract class AbstractController<E extends AbstractEntity,
        S extends CommonService<E>>
        implements CommonController<E> {

    /**
     * The service bean for working with the entities.
     */
    protected final S service;

    /**
     * The helper bean that wraps an entity in {@code EntityModel<E>}.
     */
    protected final CommonModelAssembler<E> assembler;

    /**
     * Constructs the controller and links it with the service and
     * assembler beans.
     *
     * @param service   the entity service bean
     * @param assembler the entity assembler bean
     */
    public AbstractController(S service, CommonModelAssembler<E> assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    /**
     * Returns list of all entities in the table as JSON array.
     *
     * @return JSON array with all entities in the table
     */
    @Override
    public CollectionModel<EntityModel<E>> showAll() {
        return assembler.toCollectionModel(service.getAll());
    }

    /**
     * Returns a JSON object with description of the specified entity.
     *
     * @param entityId the specified ID of the entity
     * @return the JSON object with the specified entity
     */
    @Override
    public EntityModel<E> get(@PathVariable("id") int entityId) {
        E entity = service.get(entityId);
        if (entity == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entityId + " in the database.");
        } else {
            return assembler.toModel(entity);
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
    public EntityModel<E> add(@RequestBody E entity) {
        service.saveOrUpdate(entity);
        return assembler.toModel(entity);
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
    public EntityModel<E> update(@RequestBody E entity) {
        if (service.get(entity.getId()) == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entity.getId() + " in the database.");
        } else {
            service.saveOrUpdate(entity);
            return assembler.toModel(entity);
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
    public ResponseEntity<E> delete(@PathVariable("id") int entityId) {
        E entity = service.get(entityId);
        if (entity == null) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + entityId + " in the database.");
        } else {
            service.delete(entityId);
            return ResponseEntity.noContent().build();
        }
    }
}
