package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.List;


/**
 * The interface that provides working with the contracts.
 */
public interface ContractService extends CommonService<Contract> {

    /**
     * Returns all contracts for the specified period.
     *
     * @param start the period start date
     * @param end   the period end date
     * @return contract list
     */
    List<Contract> getAll(Date start, Date end);

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
     * Adds stage to the specified contract.
     */
    ContractStage addStage(int contractId, ContractStage stage);

    /**
     * Adds counterparty contract to the specified contract.
     */
    CounterpartyContract addCounterpartyContract(
            int contractId, CounterpartyContract counterpartyContract);

    /**
     * Returns all contract in MS Excel file.
     *
     * @return MS Excel file with all contracts
     */
    ByteArrayInputStream getContractsReport(Date start, Date end);

    /**
     * Returns all stages of the specified contract in MS Excel file.
     *
     * @return MS Excel file with all contract stages
     */
    ByteArrayInputStream getStagesReport(int contractId);
}
