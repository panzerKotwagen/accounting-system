package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.model_assembler.ContractModelAssembler;


/**
 * The model assembler for {@code Contract} entity.
 */
@Component
public class ContractModelAssemblerImpl
        extends AbstractModelAssembler<Contract, ContractController>
        implements ContractModelAssembler {

    public ContractModelAssemblerImpl() {
        super(ContractController.class, "contracts");
    }
}
