package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.*;
import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;

public interface CommonController<E extends AbstractEntity> {

    //TODO: rewrite comment
    /**
     * Returns a JSON object with all entities in the table.
     *
     * @return JSON object with all entities in the table
     */
    @GetMapping
    public List<E> showAll();

    //TODO: rewrite comment
    /**
     * Returns a JSON object with description of the specified entity.
     *
     * @param entityId the specified ID of the entity
     * @return the JSON object with the specified entity
     */
    @GetMapping("/{id}")
    public E get(@PathVariable("id") int entityId);

    //TODO: rewrite comment
    /**
     * Accepts the JSON object with the description of the
     * entity and saves it into the table.
     *
     * @param entity JSON object with the description of the
     *                     entity
     * @return accepted JSON object
     */
    @PostMapping
    public E add(@RequestBody E entity) ;

    /**
     * Updates the existing entity in the table. The request
     * body must have got the JSON object with the ID of an existing
     * entity and the updated data.
     *
     * @param entity entity object with specified ID
     * @return saved object
     */
    @PutMapping
    public E update(@RequestBody E entity);

    //TODO: rewrite comment
    /**
     * Deletes the entity with the specified ID and return the
     * informative message.
     *
     * @param entityId specified ID of the entity
     * @return operation status message
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int entityId);
}
