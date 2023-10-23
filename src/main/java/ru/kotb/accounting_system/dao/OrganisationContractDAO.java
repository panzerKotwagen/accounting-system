package ru.kotb.accounting_system.dao;

import ru.kotb.accounting_system.entity.OrganisationContract;

import java.util.List;


/**
 * The DAO class used to access the database "organisationContracts".
 */
public interface OrganisationContractDAO {

    /**
     * Returns list of all organisation contracts in the table.
     *
     * @return list of all organisation contracts in the table.
     */
    List<OrganisationContract> getAllOrganisationContracts();

    /**
     * Adds new organisation contract to the table.
     *
     * @param organisationContract new organisation contract
     */
    void saveOrganisationContract(OrganisationContract organisationContract);

    /**
     * Returns the organisation contract with the specified ID.
     *
     * @param organisationContractId the ID of the contract
     * @return the organisation contract with the specified ID
     */
    OrganisationContract getOrganisationContract(int organisationContractId);

    /**
     * Deletes the contract with the specified ID in the table.
     *
     * @param organisationContractId the ID of the contract
     */
    void deleteOrganisationContract(int organisationContractId);
}
