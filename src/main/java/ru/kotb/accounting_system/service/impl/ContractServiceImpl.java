package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;
import ru.kotb.accounting_system.service.ContractService;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * The implementation of the ContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractService<Contract, ContractDAO> implements ContractService {

    private final ExcelHelper excelHelper;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ExcelHelper excelHelper) {
        super(contractDAO);
        contractDAO.setClass(Contract.class);
        this.excelHelper = excelHelper;
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
        return super.entityDAO.getAllStages(contractId);
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
    public List<CounterpartyContract> getAllOrganisationContracts(int contractId) {
        return super.entityDAO.getAllOrganisationContracts(contractId);
    }

    /**
     * Returns all contract in MS Excel file.
     *
     * @return MS Excel file with all contracts
     */
    @Transactional
    public ByteArrayInputStream getReport() {

        return excelHelper.convertToExcel(entityDAO.getAll());
    }
}
