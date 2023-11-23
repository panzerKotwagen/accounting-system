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
import ru.kotb.accounting_system.service.CommonService;
import ru.kotb.accounting_system.service.ContractService;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.List;


/**
 * The service for working with {@code Contract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractService<Contract, ContractDAO>
        implements ContractService {

    private final ExcelHelper excelHelper;

    private CommonService<CounterpartyContract> counterpartyService;

    private CommonService<ContractStage> stageService;

    @Autowired
    public void setStageService(CommonService<ContractStage> stageService) {
        this.stageService = stageService;
    }

    @Autowired
    public void setCounterpartyService(CommonService<CounterpartyContract> counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ExcelHelper excelHelper) {
        super(contractDAO);
        contractDAO.setClass(Contract.class);
        this.excelHelper = excelHelper;
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
    public List<Contract> getAll(Date start, Date end) {
        return DAO.findAllWhereDateBetween(start, end);
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
        Contract contract = getById(contractId);
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
    public List<CounterpartyContract> getAllOrganisationContracts(int contractId) {
        Contract contract = getById(contractId);
        return contract.getCounterpartyContracts();
    }

    @Override
    @Transactional
    public ContractStage addStage(int contractId, ContractStage stage)  {
        Contract contract = getById(contractId);
        stage.setContract(contract);

        return stageService.saveOrUpdate(stage);
    }

    /**
     * Adds counterparty contract to the specified contract.
     */
    @Override
    @Transactional
    public CounterpartyContract addCounterpartyContract(
            int contractId, CounterpartyContract counterpartyContract) {

        Contract contract = getById(contractId);
        counterpartyContract.setContract(contract);

        return counterpartyService.saveOrUpdate(counterpartyContract);
    }

    /**
     * Returns all contract in MS Excel file.
     *
     * @return MS Excel file with all contracts
     */
    @Override
    @Transactional
    public ByteArrayInputStream getContractsReport() {
        return excelHelper.convertContractsToExcel(DAO.findAll());
    }

    /**
     * Returns all stages of the specified contract in MS Excel file.
     *
     * @return MS Excel file with all contract stages
     */
    @Override
    @Transactional
    public ByteArrayInputStream getStagesReport(int contractId) {
        Contract contract = getById(contractId);

        return excelHelper.convertContractStagesToExcel(
                contract, contract.getContractStages());
    }
}
