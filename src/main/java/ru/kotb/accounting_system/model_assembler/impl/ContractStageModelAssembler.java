package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.impl.ContractStageController;
import ru.kotb.accounting_system.entity.ContractStage;


/**
 * The model assembler for {@code ContractStage} entity.
 */
@Component
public class ContractStageModelAssembler
        extends AbstractModelAssembler<ContractStage, ContractStageController> {

    public ContractStageModelAssembler() {
        super(ContractStageController.class, "stages");
    }
}
