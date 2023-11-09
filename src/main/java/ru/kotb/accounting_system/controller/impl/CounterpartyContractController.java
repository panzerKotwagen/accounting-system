package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.service.CounterpartyContractService;


/**
 * The controller class that processes requests to /api/organisation-contracts.
 */
@RestController
@RequestMapping("/api/organisation-contracts")
public class CounterpartyContractController
        extends AbstractController<CounterpartyContract, CounterpartyContractService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    @Autowired
    public CounterpartyContractController(CounterpartyContractService service) {
        super(service);
    }
}
