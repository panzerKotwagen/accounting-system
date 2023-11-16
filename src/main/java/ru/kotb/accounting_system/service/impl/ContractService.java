package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.IContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;
import ru.kotb.accounting_system.service.IContractService;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.List;


/**
 * The implementation of the ContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractService extends AbstractService<Contract, IContractDAO> implements IContractService {

    private final ExcelHelper excelHelper;

    @Autowired
    public ContractService(IContractDAO contractDAO, ExcelHelper excelHelper) {
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
    @Override
    @Transactional
    public ByteArrayInputStream getContractsReport() {

        return excelHelper.convertContractsToExcel(entityDAO.findAll());
    }

    /**
     * Returns all stages of the specified contract in MS Excel file.
     *
     * @return MS Excel file with all contract stages
     */
    @Override
    @Transactional
    public ByteArrayInputStream getStagesReport(int contractId) {
        Contract contract = entityDAO.findById(contractId);
        return excelHelper.convertContractStagesToExcel(
                contract, contract.getContractStages());
    }

    /**
     * Returns all contracts for the specified period.
     *
     * @param start the period start date
     * @param end   the period end date
     * @return contract list
     */
    @Override
    @Transactional
    public List<Contract> getForPeriod(Date start, Date end) {
        List<Contract> contracts = entityDAO.findAll();
        contracts.removeIf(n -> (n.getPlannedStartDate().before(start)));
        contracts.removeIf(n -> (n.getPlannedStartDate().after(end)));
        return contracts;
    }
}
