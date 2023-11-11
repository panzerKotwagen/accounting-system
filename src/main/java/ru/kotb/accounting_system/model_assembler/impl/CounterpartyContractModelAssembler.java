package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.impl.CounterpartyContractController;
import ru.kotb.accounting_system.entity.CounterpartyContract;

/**
 * The model assembler for the {@code CounterpartyContract} entity.
 */
@Component
public class CounterpartyContractModelAssembler
        extends AbstractModelAssembler<CounterpartyContract, CounterpartyContractController> {

    public CounterpartyContractModelAssembler() {
        super(CounterpartyContractController.class, "counterparty-contracts");
    }
}
