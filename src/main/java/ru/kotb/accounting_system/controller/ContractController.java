package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.OrganisationContract;

import java.util.List;


/**
 * The controller class that processes requests to /api/contracts.
 */
public interface ContractController extends CommonController<Contract> {

    /**
     * Returns the list of all stages of the contract with the
     * specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/stages")
    List<ContractStage> showAllContractStages(@PathVariable("id") int contractId);

    /**
     * Returns the list of all organisation contract of the contract
     * with the specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/organisation-contracts")
    List<OrganisationContract> showAllOrganisationContracts(
            @PathVariable("id") int contractId);
}
