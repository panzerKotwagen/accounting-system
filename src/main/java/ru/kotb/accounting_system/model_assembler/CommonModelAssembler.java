package ru.kotb.accounting_system.model_assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;


/**
 * The class that provides wrapping an entity into response with links
 * to relevant operations.
 *
 * @param <E> the entity class
 */
public interface CommonModelAssembler<E extends AbstractEntity>
        extends RepresentationModelAssembler<E, EntityModel<E>> {

    /**
     * Wraps {@code List<EntityModel<E>>} into {@code CollectionModel}.
     * @param modelList the list of entity models
     * @return {@code CollectionModel} with the list of entity models.
     */
    CollectionModel<EntityModel<E>> toCollectionModel(List<EntityModel<E>> modelList);
}
