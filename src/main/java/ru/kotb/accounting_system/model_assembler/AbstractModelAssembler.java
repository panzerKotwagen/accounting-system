package ru.kotb.accounting_system.model_assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import ru.kotb.accounting_system.controller.CommonController;
import ru.kotb.accounting_system.entity.AbstractEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * The class that provides wrapping an entity into response with links
 * to relevant operations.
 *
 * @param <E> the entity class
 * @param <T> the entity controller class
 */
public abstract class AbstractModelAssembler<E extends AbstractEntity,
        T extends CommonController<E>> implements CommonModelAssembler<E> {

    /**
     * The entity controller class.
     */
    protected Class<T> controllerClass;

    /**
     * the name of the link that leads to the list of all entities.
     */
    protected String plural;

    public AbstractModelAssembler(Class<T> controllerClass, String plural) {
        this.controllerClass = controllerClass;
        this.plural = plural;
    }

    /**
     * Wraps a list of entity models into {@code CollectionModel} with
     * relevant links.
     *
     * @param entities the list of entities
     * @return the collection model of the entity models
     */
    @Override
    public CollectionModel<EntityModel<E>> toCollectionModel(List<E> entities) {
        List<EntityModel<E>> modelList = entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelList,
                linkTo(methodOn(controllerClass).all()).withSelfRel());
    }

    /**
     * Wraps an entity into {@code EntityModel} with relevant links.
     *
     * @param entity the entity
     * @return the entity model with the entity
     */
    @Override
    public EntityModel<E> toModel(E entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(controllerClass).get(entity.getId())).withSelfRel(),
                linkTo(methodOn(controllerClass).all()).withRel(plural));
    }
}
