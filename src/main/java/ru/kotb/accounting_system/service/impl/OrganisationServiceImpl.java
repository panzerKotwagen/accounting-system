package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.OrganisationDAO;
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
    private final OrganisationDAO organisationDAO;

    @Autowired
    public OrganisationServiceImpl(OrganisationDAO organisationDAO) {
        this.organisationDAO = organisationDAO;
    }

    /**
     * Returns list of all organisations in the table.
     *
     * @return list of all organisations in the table
     */
    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationDAO.getAllOrganisations();
    }

    /**
     * Adds new organisation to the table.
     *
     * @param organisation new organisation
     */
    @Override
    public void saveOrganisation(Organisation organisation) {
        organisationDAO.saveOrganisation(organisation);
    }

    /**
     * Returns the organization with the specified ID.
     *
     * @param organisationId the ID of the organisation
     * @return the organization with the specified ID
     */
    @Override
    public Organisation getOrganisation(int organisationId) {
        return organisationDAO.getOrganisation(organisationId);
    }

    /**
     * Deletes the organization with the specified ID in the table.
     *
     * @param organisationId the ID of the organisation
     */
    @Override
    public void deleteOrganisation(int organisationId) {
        organisationDAO.deleteOrganisation(organisationId);
    }
}
