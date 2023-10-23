package ru.kotb.accounting_system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.OrganisationDAO;
import ru.kotb.accounting_system.entity.Organisation;

import java.util.List;


/**
 * The implementation of the OrganisationDAO interface.
 */
@Repository
public class OrganisationDAOImpl implements OrganisationDAO {

    /**
     * The SessionFactory object for working with database.
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public OrganisationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates the query to the database and returns list of all
     * organisations in the table.
     *
     * @return list of all organisations in the table
     */
    @Override
    public List<Organisation> getAllOrganisations() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Organisation", Organisation.class);
        return (List<Organisation>) query.getResultList();
    }

    /**
     * Saves a new or update the existing organisation in the table.
     *
     * @param organisation new organisation object
     */
    @Override
    public void saveOrganisation(Organisation organisation) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(organisation);
    }

    /**
     * Returns the organisation with the specified ID.
     *
     * @param organisationId the ID of the organisation
     * @return the organisation object with the specified ID
     */
    @Override
    public Organisation getOrganisation(int organisationId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Organisation.class, organisationId);
    }

    /**
     * Deletes the organisation with the specified ID.
     *
     * @param organisationId the ID of the organisation
     */
    @Override
    public void deleteOrganisation(int organisationId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Organisation> query = session.createQuery("delete from Organisation "
                + "where id =:organisationId");
        query.setParameter("organisationId", organisationId);
        query.executeUpdate();
    }
}
