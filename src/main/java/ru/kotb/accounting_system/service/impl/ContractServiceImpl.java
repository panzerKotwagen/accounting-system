package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.ContractService;

import java.util.List;


/**
 * The implementation of the ContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractService<Contract, ContractDAO> implements ContractService {

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO) {
        super(contractDAO);
        contractDAO.setClass(Contract.class);
    }

    /**
     * Returns all stages of the contract with the specified ID.
     *
     * @param contractId the contractID
     * @return Returns all stages of the contract with the specified ID
     */
    @Override
    @Transactional
    public List<ContractStage> getAllStages(int contractId) {
        Contract contract = super.get(contractId);
        return contract.getContractStages();
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
    @Transactional
    public List<OrganisationContract> getAllOrganisationContracts(int contractId) {
        Contract contract = super.get(contractId);
        return contract.getOrganisationContracts();
    }
}
