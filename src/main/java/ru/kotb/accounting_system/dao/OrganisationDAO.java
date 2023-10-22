package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.Organisation;

import java.util.List;


/**
 * The DAO class used to access the database "organisations".
 */
public interface OrganisationDAO {

    /**
     * Returns list of all organisations in the table.
     *
     * @return list of all organisations in the table.
     */
    List<Organisation> getAllOrganisations();

    /**
     * Adds new organisation to the table.
     *
     * @param organisation new organisation
     */
    void saveOrganisation(Organisation organisation);

    /**
     * Returns the organization with the specified ID.
     *
     * @param organisationId the ID of the organisation
     * @return the organization with the specified ID
     */
    Organisation getOrganisation(int organisationId);

    /**
     * Deletes the organization with the specified ID in the table.
     *
     * @param organisationId the ID of the organisation
     */
    void deleteOrganisation(int organisationId);
}
