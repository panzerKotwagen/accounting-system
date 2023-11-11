package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.CounterpartyContractController;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.model_assembler.CounterpartyContractModelAssembler;
import ru.kotb.accounting_system.service.CounterpartyContractService;


/**
 * The controller class that processes requests to /api/counterparty-contracts.
 */
@RestController
public class CounterpartyContractControllerImpl
        extends AbstractController<CounterpartyContract, CounterpartyContractService>
        implements CounterpartyContractController {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    @Autowired
    public CounterpartyContractControllerImpl(
            CounterpartyContractService service,
            CounterpartyContractModelAssembler assembler) {
        super(service, assembler);
    }
}
