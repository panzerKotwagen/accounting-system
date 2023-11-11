package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.IContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * The implementation of the ContractDAO interface.
 */
@Repository
//TODO: replace using Hibernate with JPA
public class ContractDAO extends AbstractDAO<Contract> implements IContractDAO {

    /**
     * Creates the component and binds it with the entityManager
     * object.
     *
     * @param entityManager the EntityManager object
     */
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
    @Override
    public List<ContractStage> getAllStages(int contractId) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
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
    @Override
    public List<CounterpartyContract> getAllOrganisationContracts(int contractId) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "SELECT c.counterpartyContracts FROM Contract c " +
                        "WHERE c.id = :contractId");
        query.setParameter("contractId", contractId);

        return (List<CounterpartyContract>) query.getResultList();
    }
}
