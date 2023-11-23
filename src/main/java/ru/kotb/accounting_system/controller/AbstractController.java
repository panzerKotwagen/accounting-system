package ru.kotb.accounting_system.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kotb.accounting_system.entity.AbstractEntity;
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
     * Returns {@code CollectionModel} of the entities.
     */
    @Override
    public CollectionModel<EntityModel<E>> all() {
        return assembler.toCollectionModel(service.getAll());
    }

    /**
     * Returns {@code EntityModel} with the specified entity.
     *
     * @param entityId the specified ID of the entity
     */
    @Override
    public EntityModel<E> get(@PathVariable("id") int entityId) {
        return assembler.toModel(service.getById(entityId));
    }

    /**
     * Saves the entity into the table. The request body must have
     * got the JSON object with the data of the new entity.
     *
     * @param entity the entity object
     * @return saved object
     */
    @Override
    public ResponseEntity<?> add(@RequestBody E entity) {
        EntityModel<E> entityModel = assembler.toModel(
                service.saveOrUpdate(entity));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
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
    public ResponseEntity<?> update(@RequestBody E entity) {
        EntityModel<E> entityModel = assembler
                .toModel(service.saveOrUpdate(entity));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    /**
     * Deletes the entity with the specified ID and return the
     * informative message.
     *
     * @param entityId specified ID of the entity
     * @return HTTP 204
     */
    @Override
    public ResponseEntity<E> delete(@PathVariable("id") int entityId) {
        service.deleteById(entityId);
        return ResponseEntity.noContent().build();
    }
}
