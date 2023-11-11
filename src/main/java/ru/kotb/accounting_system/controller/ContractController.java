package ru.kotb.accounting_system.controller;

import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.dto.DatePeriodDTO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;


/**
 * The controller class that processes requests to /api/contracts.
 */
@RequestMapping("/api/contracts")
public interface ContractController extends CommonController<Contract> {

    /**
     * Returns the list of all stages of the contract with the
     * specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/stages")
    CollectionModel<EntityModel<ContractStage>> showAllContractStages(@PathVariable("id") int contractId);

    /**
     * Returns the list of all organisation contract of the contract
     * with the specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/counterparty-contracts")
    CollectionModel<EntityModel<CounterpartyContract>> showAllOrganisationContracts(
            @PathVariable("id") int contractId);

    /**
     * Returns the Excel file with the all contracts.
     *
     * @return the Excel file with the all contracts
     */
    @GetMapping("/report/download")
    ResponseEntity<Resource> downloadContractsReport();

    /**
     * Return the Excel file with the all stages of the specified
     * contract.
     *
     * @param contractId the id of the contract
     * @return the Excel file with the all stages of the specified
     * contract
     */
    @GetMapping("{id}/stages/report/download")
    ResponseEntity<Resource> downloadStagesReport(
            @PathVariable("id") int contractId);

    /**
     * Returns all contracts for the specified period, or just all
     * contracts if it was not specified.
     *
     * @param periodDTO DTO with start and end date of the period
     * @return JSON array with all contracts in the table
     */
    @GetMapping("/")
    CollectionModel<EntityModel<Contract>> showAll(@RequestBody(required = false) DatePeriodDTO periodDTO);
}
