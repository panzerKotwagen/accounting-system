package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import java.util.List;


/**
 * The DAO interface used to access the table "contracts".
 */
public interface IContractDAO extends ICommonDAO<Contract> {

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
}
