package ru.kotb.accountingsystem.dao;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.configuration.TestConfig;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;

import java.util.List;


@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class StageDAOTest {

    @Autowired
    private StageDAO stageDAO;

    @Autowired
    private ContractDAO contractDAO;

    private Contract contract;

    @BeforeEach
    public void init() {
        contract = new Contract();
        ContractStage stage1 = new ContractStage();
        ContractStage stage2 = new ContractStage();

        stage1.setContract(contract);
        stage2.setContract(contract);

        contract.getContractStages().add(stage1);
        contract.getContractStages().add(stage2);

        contract = contractDAO.save(contract);
    }

    @Test
    public void findAllByContractIdReturnStages() {
        List<ContractStage> stages = stageDAO.findAllByContractId(contract.getId());

        Assertions.assertThat(stages.size()).isEqualTo(2);
    }

    @Test
    public void deleteContractDeleteStages() {
        contractDAO.deleteById(contract.getId());

        List<ContractStage> stages = stageDAO.findAllByContractId(contract.getId());

        Assertions.assertThat(stages.size()).isEqualTo(0);
    }

    @Test
    public void deleteStageDeleteStageFromContract() {
        int stageId = contract.getContractStages().get(0).getId();

        stageDAO.deleteById(stageId);

        List<ContractStage> stages = stageDAO.findAllByContractId(contract.getId());
        contract = contractDAO.findById(contract.getId()).get();

        Assertions.assertThat(stages.size()).isEqualTo(1);
        Assertions.assertThat(contract.getContractStages().size()).isEqualTo(1);
    }

    @Test
    public void updateStageUpdatesItInTheContract() {
        ContractStage stage = contract.getContractStages().get(0);

        stage.setName("Test");
        stageDAO.save(stage);

        ContractStage updatedStage = contract.getContractStages().get(0);
        Assertions.assertThat(updatedStage.getName()).isEqualTo("Test");
    }
}
