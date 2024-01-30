package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.dao.CommonDAO;
import ru.kotb.accountingsystem.dao.ContractDAO;
import ru.kotb.accountingsystem.dao.StageDAO;
import ru.kotb.accountingsystem.dto.ContractDTO;
import ru.kotb.accountingsystem.entity.AbstractContract;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;
import ru.kotb.accountingsystem.entity.CounterpartyContract;
import ru.kotb.accountingsystem.entity.group.ContractNull;
import ru.kotb.accountingsystem.excelhelper.ExcelHelper;
import ru.kotb.accountingsystem.service.ContractService;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * The service for working with {@code Contract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractServiceOld<Contract, ContractDAO>
        implements ContractService {

    private final ExcelHelper excelHelper;

    private final CommonDAO<CounterpartyContract> counterpartyDAO;

    private final StageDAO stageDAO;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO,
                               ExcelHelper excelHelper,
                               CommonDAO<CounterpartyContract> counterpartyDAO,
                               StageDAO stageDAO) {

        super(contractDAO);
        this.excelHelper = excelHelper;
        this.counterpartyDAO = counterpartyDAO;
        this.stageDAO = stageDAO;
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

        return stageDAO.save(stage);
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

        return counterpartyDAO.save(counterpartyContract);
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

        contractDTOList.sort(Comparator.comparing(AbstractContract::getPlannedStartDate));

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
