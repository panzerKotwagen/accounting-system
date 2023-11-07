package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * The interface that provides working with the contracts.
 */
public interface ContractService extends CommonService<Contract> {

    /**
     * Returns all stages of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return Returns all stages of the contract with the specified ID
     */
    List<ContractStage> getAllStages(int contractId);

    /**
     * Returns all contracts with the counterparty
     * organisations of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return all contracts with the counterparty
     * organisations of the contract with the specified ID
     */
    List<CounterpartyContract> getAllOrganisationContracts(int contractId);

    /**
     * Returns all contract in MS Excel file.
     *
     * @return MS Excel file with all contracts
     */
    ByteArrayInputStream getReport();
}
