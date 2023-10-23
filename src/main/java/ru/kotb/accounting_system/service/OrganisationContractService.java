package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.entity.OrganisationContract;

import java.util.List;


/**
 * The class that provides working with the organisation contracts.
 */
public interface OrganisationContractService {

    /**
     * Returns list of all organisation contracts in the table.
     *
     * @return list of all contracts in the table
     */
    List<OrganisationContract> getAllOrganisationContracts();

    /**
     * Adds new organisation contract to the table.
     *
     * @param organisationContract new contract
     */
    void saveOrganisationContract(OrganisationContract organisationContract);

    /**
     * Returns the organization with the specified ID.
     *
     * @param organisationContractId the ID of the contract
     * @return the organization with the specified ID
     */
    OrganisationContract getOrganisationContract(int organisationContractId);

    /**
     * Deletes the organization with the specified ID in the table.
     *
     * @param organisationContractId the ID of the contract
     */
    void deleteOrganisationContract(int organisationContractId);
}
