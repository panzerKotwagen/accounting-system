package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.GenericDAO;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.service.OrganisationService;

import java.util.List;

/**
 * The implementation of the OrganisationService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationServiceImpl implements OrganisationService {

    /**
     * The DAO object for getting access to the "organisations" table.
     */
    private final GenericDAO<Organisation> organisationDAO;

    @Autowired
    public OrganisationServiceImpl(GenericDAO<Organisation> genericDAOImpl) {
        this.organisationDAO = genericDAOImpl;
        this.organisationDAO.setClass(Organisation.class);
    }

    /**
     * Returns list of all organisations in the table.
     *
     * @return list of all organisations in the table
     */
    @Override
    @Transactional
    public List<Organisation> getAllOrganisations() {
        return organisationDAO.getAll();
    }

    /**
     * Adds new organisation to the table.
     *
     * @param organisation new organisation
     */
    @Override
    @Transactional
    public void saveOrganisation(Organisation organisation) {
        organisationDAO.saveOrUpdate(organisation);
    }

    /**
     * Returns the organization with the specified ID.
     *
     * @param organisationId the ID of the organisation
     * @return the organization with the specified ID
     */
    @Override
    @Transactional
    public Organisation getOrganisation(int organisationId) {
        return organisationDAO.get(organisationId);
    }

    /**
     * Deletes the organization with the specified ID in the table.
     *
     * @param organisationId the ID of the organisation
     */
    @Override
    @Transactional
    public void deleteOrganisation(int organisationId) {
        organisationDAO.delete(organisationId);
    }
}
