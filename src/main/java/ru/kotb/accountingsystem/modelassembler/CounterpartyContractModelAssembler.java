package ru.kotb.accountingsystem.modelassembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.controller.ContractController;
import ru.kotb.accountingsystem.controller.CounterpartyContractController;
import ru.kotb.accountingsystem.controller.OrganisationController;
import ru.kotb.accountingsystem.entity.CounterpartyContract;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The model assembler for the {@code CounterpartyContract} entity.
 */
@Component
public class CounterpartyContractModelAssembler
        extends AbstractModelAssembler<CounterpartyContract, CounterpartyContractController> {

    public CounterpartyContractModelAssembler() {
        super(CounterpartyContractController.class, "counterparty-contracts");
    }

    /**
     * Wraps an entity into {@code EntityModel} with relevant links.
     *
     * @param entity the entity
     * @return the entity model with the entity
     */
    @Override
    public EntityModel<CounterpartyContract> toModel(CounterpartyContract entity) {
        EntityModel<CounterpartyContract> entityModel = super.toModel(entity);
        int associatedContractId = entity.getContract().getId();
        int associatedOrgId = entity.getOrganisation().getId();
        entityModel.add(
                linkTo(methodOn(ContractController.class).get(associatedContractId)).withRel("contract"));
        entityModel.add(
                linkTo(methodOn(OrganisationController.class).get(associatedOrgId)).withRel("organisation"));
        return entityModel;
    }
}
