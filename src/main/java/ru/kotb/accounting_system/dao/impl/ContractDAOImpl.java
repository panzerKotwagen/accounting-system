package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.OrganisationContract;

import java.util.List;


/**
 * The implementation of the ContractDAO interface.
 */
@Repository
public class ContractDAOImpl extends AbstractDAO<Contract> implements ContractDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public ContractDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Returns all stages of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return Returns all stages of the contract with the specified ID
     */
    @Override
    public List<ContractStage> getAllStages(int contractId) {
        Session session = sessionFactory.getCurrentSession();

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
    public List<OrganisationContract> getAllOrganisationContracts(int contractId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(
                "SELECT c.organisationContracts FROM Contract c " +
                        "WHERE c.id = :contractId");
        query.setParameter("contractId", contractId);

        return (List<OrganisationContract>) query.getResultList();
    }
}
