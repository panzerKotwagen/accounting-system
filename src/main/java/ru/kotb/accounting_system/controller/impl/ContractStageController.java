package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.service.ContractStageService;


/**
 * The controller class that processes requests to /api/contract-stages.
 */
@RestController
@RequestMapping("/api/contract-stages")
public class ContractStageController
        extends AbstractController<ContractStage, ContractStageService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param contractStageService the contract stage service bean
     */
    @Autowired
    public ContractStageController(ContractStageService contractStageService) {
        super(contractStageService);
    }
}
