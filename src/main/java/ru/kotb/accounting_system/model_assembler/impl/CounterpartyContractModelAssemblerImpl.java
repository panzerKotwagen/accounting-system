package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.CounterpartyContractController;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.model_assembler.CounterpartyContractModelAssembler;

/**
 * The model assembler for the {@code CounterpartyContract} entity.
 */
@Component
public class CounterpartyContractModelAssemblerImpl
        extends AbstractModelAssembler<CounterpartyContract, CounterpartyContractController>
        implements CounterpartyContractModelAssembler {


    public CounterpartyContractModelAssemblerImpl() {
        super(CounterpartyContractController.class, "counterparty-contracts");
    }
}
