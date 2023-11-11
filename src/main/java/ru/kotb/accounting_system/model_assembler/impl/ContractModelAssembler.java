package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.entity.Contract;


/**
 * The model assembler for {@code Contract} entity.
 */
@Component
public class ContractModelAssembler
        extends AbstractModelAssembler<Contract, ContractController> {

    public ContractModelAssembler() {
        super(ContractController.class, "contracts");
    }
}
