package ru.kotb.accountingsystem.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kotb.accountingsystem.repository.CommonDAO;
import ru.kotb.accountingsystem.repository.ContractDAO;
import ru.kotb.accountingsystem.repository.StageDAO;
import ru.kotb.accountingsystem.dto.ContractDTO;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;
import ru.kotb.accountingsystem.entity.CounterpartyContract;
import ru.kotb.accountingsystem.excelhelper.ExcelHelper;
import ru.kotb.accountingsystem.service.impl.ContractServiceImpl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

    @Mock
    private ExcelHelper excelHelper;

    @Mock
    private ContractDAO contractDAO;

    @Mock
    private StageDAO stageDAO;

    @Mock
    private CommonDAO<CounterpartyContract> counterpartyContractDAO;

    @InjectMocks
    private ContractServiceImpl contractService;

    @Test
    void getAll() {
        contractService.getAll();
        verify(contractDAO).findAll();
    }

    @Test
    void getAllWhereDateBetween() {
        // given
        Date start = Date.valueOf("2023-12-05");
        Date end = Date.valueOf("2023-12-30");

        // when
        contractService.getAll(start, end);

        // then
        verify(contractDAO).findAllWhereDateBetween(start, end);
    }

    @Test
    void getAllStages() {
        // given
        int contractId = 1;
        ContractStage stage = new ContractStage();
        Contract contract = new Contract();
        contract.getContractStages().add(stage);
        contract.setId(contractId);
        given(contractDAO.findById(contractId)).willReturn(Optional.of(contract));

        // when
        List<ContractStage> stages = contractService.getAllStages(contractId);

        // then
        Assertions.assertThat(stages).isEqualTo(contract.getContractStages());
    }

    @Test
    void getAllOrganisationContracts() {
        // given
        int contractId = 1;
        CounterpartyContract cc = new CounterpartyContract();
        Contract contract = new Contract();
        contract.getCounterpartyContracts().add(cc);
        contract.setId(contractId);
        given(contractDAO.findById(contractId)).willReturn(Optional.of(contract));

        // when
        List<CounterpartyContract> contracts = contractService.getAllOrganisationContracts(contractId);

        // then
        Assertions.assertThat(contracts).isEqualTo(contract.getCounterpartyContracts());
    }

    @Test
    void addStage() {
        // given
        int contractId = 1;
        ContractStage stage = new ContractStage();
        Contract contract = new Contract();
        contract.setId(contractId);
        given(contractDAO.findById(contractId)).willReturn(Optional.of(contract));
        given(stageDAO.save(stage)).willReturn(stage);

        // when
        contractService.addStage(contractId, stage);

        // then
        ArgumentCaptor<ContractStage> stageArgumentCaptor = ArgumentCaptor.forClass(ContractStage.class);

        verify(stageDAO).save(stageArgumentCaptor.capture());

        ContractStage captorValue = stageArgumentCaptor.getValue();

        Assertions.assertThat(captorValue).isEqualTo(stage);
    }

    @Test
    void addCounterpartyContract() {
        // given
        int contractId = 1;
        CounterpartyContract cc = new CounterpartyContract();
        Contract contract = new Contract();
        contract.setId(contractId);
        given(contractDAO.findById(contractId)).willReturn(Optional.of(contract));
        given(counterpartyContractDAO.save(cc)).willReturn(cc);

        // when
        contractService.addCounterpartyContract(contractId, cc);

        // then
        ArgumentCaptor<CounterpartyContract> stageArgumentCaptor = ArgumentCaptor.forClass(CounterpartyContract.class);

        verify(counterpartyContractDAO).save(stageArgumentCaptor.capture());

        CounterpartyContract captorValue = stageArgumentCaptor.getValue();

        Assertions.assertThat(captorValue).isEqualTo(cc);
    }

    @Test
    void getContractsReport() {
        // given
        Date start = Date.valueOf("1023-12-05");
        Date end = Date.valueOf("2023-12-31");

        Contract contract1 = new Contract();
        Contract contract2 = new Contract();
        CounterpartyContract cc1 = new CounterpartyContract();

        cc1.setContract(contract2);
        contract2.getCounterpartyContracts().add(cc1);

        contract1.setPlannedStartDate(Date.valueOf("1812-12-12"));
        contract2.setPlannedStartDate(Date.valueOf("2023-12-05"));
        cc1.setPlannedStartDate(Date.valueOf("1023-12-05"));

        given(contractDAO.findAllWhereDateBetween(start, end)).willReturn(Arrays.asList(contract1, contract2));

        // when
        contractService.getContractsReport(start, end);

        // then
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

        verify(excelHelper).convertContractsToExcel(captor.capture());

        List<ContractDTO> captorValue = captor.getValue();

        ContractDTO contractDTO1 = captorValue.get(0);
        ContractDTO contractDTO2 = captorValue.get(1);
        ContractDTO contractDTO3 = captorValue.get(2);

        Assertions.assertThat(contractDTO1.getPlannedStartDate())
                .isEqualTo(cc1.getPlannedStartDate());
        Assertions.assertThat(contractDTO2.getPlannedStartDate())
                .isEqualTo(contract1.getPlannedStartDate());
        Assertions.assertThat(contractDTO3.getPlannedStartDate())
                .isEqualTo(contract2.getPlannedStartDate());
    }
}
