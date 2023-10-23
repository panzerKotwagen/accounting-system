package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.OrganisationContractDAO;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.OrganisationContractService;

import java.util.List;

/**
 * The implementation of the OrganisationContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationContractServiceImpl implements OrganisationContractService {

    /**
     * The DAO object for getting access to the "organisation_contracts" table.
     */
    private final OrganisationContractDAO organisationContractDAO;

    @Autowired
    public OrganisationContractServiceImpl(OrganisationContractDAO organisationContractDAO) {
        this.organisationContractDAO = organisationContractDAO;
    }

    /**
     * Returns list of all organisation contracts in the table.
     *
     * @return list of all organisation contracts in the table
     */
    @Override
    @Transactional
    public List<OrganisationContract> getAllOrganisationContracts() {
        return organisationContractDAO.getAllOrganisationContracts();
    }

    /**
     * Adds new organisationContract to the table.
     *
     * @param organisationContract new contract
     */
    @Override
    @Transactional
    public void saveOrganisationContract(OrganisationContract organisationContract) {
        organisationContractDAO.saveOrganisationContract(organisationContract);
    }

    /**
     * Returns the contract with the specified ID.
     *
     * @param organisationContractId the ID of the contract
     * @return the contract with the specified ID
     */
    @Override
    @Transactional
    public OrganisationContract getOrganisationContract(int organisationContractId) {
        return organisationContractDAO.getOrganisationContract(organisationContractId);
    }

    /**
     * Deletes the contract with the specified ID in the table.
     *
     * @param organisationContractId the ID of the contract
     */
    @Override
    @Transactional
    public void deleteOrganisationContract(int organisationContractId) {
        organisationContractDAO.deleteOrganisationContract(organisationContractId);
    }
}
