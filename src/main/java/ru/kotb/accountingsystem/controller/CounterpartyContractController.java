package ru.kotb.accountingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accountingsystem.entity.CounterpartyContract;
import ru.kotb.accountingsystem.modelassembler.CommonModelAssembler;
import ru.kotb.accountingsystem.service.CommonService;


/**
 * The controller class that processes requests to /api/counterparty-contracts.
 */
@RestController
@RequestMapping("/api/counterparty-contracts")
public class CounterpartyContractController
        extends AbstractController<CounterpartyContract,
        CommonService<CounterpartyContract>> {

    @Autowired
    public CounterpartyContractController(
            CommonService<CounterpartyContract> service,
            CommonModelAssembler<CounterpartyContract> assembler) {
        super(service, assembler);
    }
}
