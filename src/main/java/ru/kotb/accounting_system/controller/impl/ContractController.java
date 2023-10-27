package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.service.ContractService;


/**
 * The controller class that processes requests to /api/contracts.
 */
@RestController
@RequestMapping("/api/contracts")
public class ContractController extends AbstractController<Contract, ContractService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param contractService the contract service bean
     */
    @Autowired
    public ContractController(ContractService contractService) {
        super(contractService);
    }
}
