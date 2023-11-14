package ru.kotb.accounting_system.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.entity.Contract;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * The model assembler for {@code Contract} entity.
 */
@Component
public class ContractModelAssembler
        extends AbstractModelAssembler<Contract, ContractController> {

    public ContractModelAssembler() {
        super(ContractController.class, "contracts");
    }

    /**
     * Wraps an entity into {@code EntityModel} with relevant links.
     *
     * @param entity the entity
     * @return the entity model with the entity
     */
    @Override
    public EntityModel<Contract> toModel(Contract entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(controllerClass).get(entity.getId()))
                        .withSelfRel(),

                linkTo(methodOn(controllerClass).allStages(entity.getId()))
                        .withRel("stages"),

                linkTo(methodOn(controllerClass).allCounterpartyContracts(
                        entity.getId())).withRel("counterparty-contracts"),

                linkTo(methodOn(controllerClass).all()).withRel(plural));

    }
}
