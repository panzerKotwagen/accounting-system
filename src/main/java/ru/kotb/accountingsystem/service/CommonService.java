package ru.kotb.accountingsystem.service;

import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.entity.AbstractEntity;

import javax.validation.Valid;
import java.util.List;


/**
 * The generic interface that provides working with the specified
 * entity.
 */
@Validated
public interface CommonService<E extends AbstractEntity> {

    /**
     * Returns list of all entities in the table.
     *
     * @return list of all entities in the table
     */
    List<E> getAll();

    /**
     * Adds a new entity to the table or updates the existing.
     *
     * @param entity new entity
     */
    E save(@Valid E entity);

    /**
     * Updates the specified entity. When the entity with given id
     * was not found then throw {@code NoSuchEntityException}.
     */
    E update(@Valid E entity);

    /**
     * Returns the entity with the specified ID.
     *
     * @param entityId the ID of the entity
     * @return the entity with the specified ID
     */
    E getById(int entityId);

    /**
     * Deletes the entity with the specified ID in the table.
     *
     * @param entityId the ID of the entity
     */
    void deleteById(int entityId);
}
