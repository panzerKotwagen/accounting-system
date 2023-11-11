package ru.kotb.accounting_system.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kotb.accounting_system.entity.AbstractEntity;


/**
 * The interface that describes REST controller with CRUD operations.
 *
 * @param <E> the entity class that the controller works with
 */
public interface CommonController<E extends AbstractEntity> {

    /**
     * Returns list of all entities in the table as JSON array.
     *
     * @return JSON array with all entities in the table
     */
    @GetMapping
    CollectionModel<EntityModel<E>> showAll();

    /**
     * Returns a JSON object with description of the specified entity.
     *
     * @param entityId the specified ID of the entity
     * @return the JSON object with the specified entity
     */
    @GetMapping("/{id}")
    EntityModel<E> get(@PathVariable("id") int entityId);

    /**
     * Saves the entity into the table. The request body must have
     * got the JSON object with the data of the new entity.
     *
     * @param entity the entity object
     * @return saved object
     */
    @PostMapping
    EntityModel<E> add(@RequestBody E entity);

    /**
     * Updates the existing entity in the table. The request
     * body must have got the JSON object with the ID of an existing
     * entity and the updated data.
     *
     * @param entity entity object with specified ID
     * @return saved object
     */
    @PutMapping
    EntityModel<E> update(@RequestBody E entity);

    /**
     * Deletes the entity with the specified ID and return the
     * informative message.
     *
     * @param entityId specified ID of the entity
     * @return operation status message
     */
    @DeleteMapping("/{id}")
    ResponseEntity<E> delete(@PathVariable("id") int entityId);
}
