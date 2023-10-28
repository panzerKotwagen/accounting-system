package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.entity.Contract;

import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.ContractService;

import java.util.List;


/**
 * The controller class that processes requests to /api/contracts.
 */
@RestController
@RequestMapping("/api/contracts")
public class ContractControllerImpl
        extends AbstractController<Contract, ContractService>
        implements ContractController {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param contractService the contract service bean
     */
    @Autowired
    public ContractControllerImpl(ContractService contractService) {
        super(contractService);
    }

    /**
     * Returns the list of all stages of the contract with the
     * specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @Override
    public List<ContractStage> showAllContractStages(int contractId) {
        return service.getAllStages(contractId);
    }

    /**
     * Returns the list of all organisation contract of the contract
     * with the specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @Override
    public List<OrganisationContract> showAllOrganisationContracts(int contractId) {
        return service.getAllOrganisationContracts(contractId);
    }
}
