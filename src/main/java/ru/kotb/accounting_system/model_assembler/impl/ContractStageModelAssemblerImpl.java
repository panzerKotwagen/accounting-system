package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.ContractStageController;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.model_assembler.ContractStageModelAssembler;


/**
 * The model assembler for {@code ContractStage} entity.
 */
@Component
public class ContractStageModelAssemblerImpl
        extends AbstractModelAssembler<ContractStage, ContractStageController>
        implements ContractStageModelAssembler {

    public ContractStageModelAssemblerImpl() {
        super(ContractStageController.class, "stages");
    }
}
