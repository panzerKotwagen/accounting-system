package ru.kotb.accounting_system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * The DAO to work with the {@code Controller} entity.
 */
@Repository
public class ContractDAO extends AbstractDAO<Contract> {

    @Autowired
    public ContractDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Returns all stages of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return Returns all stages of the contract with the specified ID
     */
    public List<ContractStage> getAllStages(int contractId) {
        Query query = entityManager.createQuery(
                "SELECT c.contractStages FROM Contract c " +
                        "WHERE c.id = :contractId");
        query.setParameter("contractId", contractId);

        return (List<ContractStage>) query.getResultList();
    }

    /**
     * Returns all contracts with the counterparty
     * organisations of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return all contracts with the counterparty
     * organisations of the contract with the specified ID
     */
    public List<CounterpartyContract> getAllOrganisationContracts(int contractId) {
        Query query = entityManager.createQuery(
                "SELECT c.counterpartyContracts FROM Contract c " +
                        "WHERE c.id = :contractId");
        query.setParameter("contractId", contractId);

        return (List<CounterpartyContract>) query.getResultList();
    }
}
