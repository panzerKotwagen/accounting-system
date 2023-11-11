package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.CommonController;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.CounterpartyContractService;


/**
 * The controller class that processes requests to /api/counterparty-contracts.
 */
@RestController
@RequestMapping("/api/counterparty-contracts")
public class CounterpartyContractController
        extends AbstractController<CounterpartyContract, CounterpartyContractService>
        implements CommonController<CounterpartyContract> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    @Autowired
    public CounterpartyContractController(
            CounterpartyContractService service,
            CommonModelAssembler<CounterpartyContract> assembler) {
        super(service, assembler);
    }
}
