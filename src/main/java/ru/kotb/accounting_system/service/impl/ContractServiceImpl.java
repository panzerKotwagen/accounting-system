package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.dto.ContractDTO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.entity.group.ContractNull;
import ru.kotb.accounting_system.excel_helper.ExcelHelper;
import ru.kotb.accounting_system.service.CommonService;
import ru.kotb.accounting_system.service.ContractService;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * The service for working with {@code Contract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractService<Contract, ContractDAO>
        implements ContractService {

    private final ExcelHelper excelHelper;

    private final CommonService<CounterpartyContract> counterpartyService;

    private final CommonService<ContractStage> stageService;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ExcelHelper excelHelper, CommonService<CounterpartyContract> counterpartyService, CommonService<ContractStage> stageService) {
        super(contractDAO);
        this.excelHelper = excelHelper;
        this.counterpartyService = counterpartyService;
        this.stageService = stageService;
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
    public ContractStage addStage(int contractId, @Validated({ContractNull.class}) ContractStage stage)  {
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
            int contractId, @Validated({ContractNull.class}) CounterpartyContract counterpartyContract) {

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
    public ByteArrayInputStream getContractsReport(Date start, Date end) {
        List<Contract> contracts = getAll(start, end);

        List<ContractDTO> contractDTOList = new ArrayList<>();
        for (Contract c : contracts) {
            contractDTOList.add(new ContractDTO(c));

            for (CounterpartyContract cc : c.getCounterpartyContracts()) {
                contractDTOList.add(new ContractDTO(cc));
            }
        }

        contractDTOList.sort((ContractDTO a1, ContractDTO a2)
                -> a1.getPlannedStartDate().compareTo(a2.getActualStartDate()));

        return excelHelper.convertContractsToExcel(contractDTOList);
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
