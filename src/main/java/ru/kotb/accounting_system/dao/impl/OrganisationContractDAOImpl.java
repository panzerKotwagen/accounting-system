package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.OrganisationContractDAO;
import ru.kotb.accounting_system.entity.OrganisationContract;

import java.util.List;


/**
 * The implementation of the OrganisationContractDAO interface.
 */
@Repository
public class OrganisationContractDAOImpl implements OrganisationContractDAO {

    /**
     * The SessionFactory object for working with database.
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public OrganisationContractDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates the query to the database and returns list of all
     * organisations in the table.
     *
     * @return list of all organisations in the table
     */
    @Override
    public List<OrganisationContract> getAllOrganisationContracts() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from OrganisationContract",
                OrganisationContract.class);
        List<OrganisationContract> organisationContracts = query.getResultList();

        return organisationContracts;
    }

    /**
     * Saves the new or update the existing contract in the table.
     *
     * @param organisationContract new contract object
     */
    @Override
    public void saveOrganisationContract(OrganisationContract organisationContract) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(organisationContract);
    }

    /**
     * Returns the contract with the specified ID.
     *
     * @param organisationContractId the ID of the organisation
     * @return the contract object with the specified ID
     */
    @Override
    public OrganisationContract getOrganisationContract(int organisationContractId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrganisationContract.class, organisationContractId);
    }

    /**
     * Deletes the contract with the specified ID from the table.
     * @param organisationContractId the ID of the organisation
     */
    @Override
    public void deleteOrganisationContract(int organisationContractId) {
        Session session = sessionFactory.getCurrentSession();
        Query<OrganisationContract> query = session.createQuery(
                "delete from OrganisationContract "
                + "where id =:organisationContractId");
        query.setParameter("organisationContractId", organisationContractId);
        query.executeUpdate();
    }
}
