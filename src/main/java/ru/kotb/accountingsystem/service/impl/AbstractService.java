package ru.kotb.accountingsystem.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.dao.CommonRepository;
import ru.kotb.accountingsystem.entity.AbstractEntity;
import ru.kotb.accountingsystem.exception.handling.NoSuchEntityException;
import ru.kotb.accountingsystem.service.CommonService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * The abstract service provides following operations with an entity:
 * getting all records, getting a certain object by ID, adding a new
 * one and deleting it by ID. Each class that extends it must specify
 * the entity type in the parameter and set the entity class via
 * setter in the constructor.
 *
 * @param <E> the class of the entity
 * @param <R> the class of the repository
 */
@Validated
public abstract class AbstractService<E extends AbstractEntity,
        R extends CommonRepository<E>> implements CommonService<E> {

    /**
     * The repository for getting access to the specified table.
     */
    protected final R repository;

    /**
     * Links the specified repository with the service.
     *
     * @param genericRepository the repository of the specified entity class
     */
    public AbstractService(R genericRepository) {
        this.repository = genericRepository;
    }

    /**
     * Returns list of all entities in the table.
     *
     * @return list of all entities in the table
     */
    @Override
    @Transactional
    public List<E> getAll() {
        return repository.findAll();
    }

    /**
     * Adds new entity to the table.
     *
     * @param entity new entity
     */
    @Override
    @Transactional
    public E save(@Valid E entity) {
        return repository.save(entity);
    }

    /**
     * Updates the specified entity. When the entity with given id
     * was not found then throw {@code NoSuchEntityException}.
     */
    @Override
    @Transactional
    public E update(@Valid E entity) {
        repository.findById(entity.getId()).orElseThrow(
                () -> new NoSuchEntityException("There is no entity with ID = "
                        + entity.getId() + " in the database."));

        return repository.save(entity);
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param id the ID of the entity
     * @return the entity with the specified ID
     */
    @Override
    @Transactional
    public E getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("There is no entity with ID = "
                        + id + " in the database."));
    }

    /**
     * Deletes the entity with the specified ID in the table.
     *
     * @param id the ID of the entity
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        try {
            repository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchEntityException("There is no entity with ID = "
                    + id + " in the database.");
        }
    }
}
