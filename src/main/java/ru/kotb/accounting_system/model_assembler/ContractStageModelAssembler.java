package ru.kotb.accounting_system.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.controller.ContractStageController;
import ru.kotb.accounting_system.entity.ContractStage;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * The model assembler for {@code ContractStage} entity.
 */
@Component
public class ContractStageModelAssembler
        extends AbstractModelAssembler<ContractStage, ContractStageController> {

    public ContractStageModelAssembler() {
        super(ContractStageController.class, "stages");
    }

    /**
     * Wraps an entity into {@code EntityModel} with relevant links.
     *
     * @param entity the entity
     * @return the entity model with the entity
     */
    @Override
    public EntityModel<ContractStage> toModel(ContractStage entity) {
        EntityModel<ContractStage> entityModel = super.toModel(entity);
        int associatedContractId = entity.getContract().getId();
        entityModel.add(
                linkTo(methodOn(ContractController.class).get(associatedContractId)).withRel("contract"));

        return entityModel;
    }
}
