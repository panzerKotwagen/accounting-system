package ru.kotb.accountingsystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.ContractStage;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class StageRepositoryTest {

    @Autowired
    private StageRepository stageRep;

    @Autowired
    private ContractRepository contractRep;

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

        contract = contractRep.save(contract);
    }

    @Test
    public void findByContractIdReturnStages() {
        List<ContractStage> stages = stageRep.findByContractId(contract.getId());

        Assertions.assertThat(stages.size()).isEqualTo(2);
    }

    @Test
    public void deleteContractDeleteStages() {
        contractRep.deleteById(contract.getId());

        List<ContractStage> stages = stageRep.findByContractId(contract.getId());

        Assertions.assertThat(stages.size()).isEqualTo(0);
    }

    @Test
    public void deleteStageDeleteStageFromContract() {
        int stageId = contract.getContractStages().get(0).getId();

        stageRep.deleteById(stageId);

        List<ContractStage> stages = stageRep.findByContractId(contract.getId());
        contract = contractRep.findById(contract.getId()).get();

        Assertions.assertThat(stages.size()).isEqualTo(1);
        Assertions.assertThat(contract.getContractStages().size()).isEqualTo(1);
    }

    @Test
    public void updateStageUpdatesItInTheContract() {
        ContractStage stage = contract.getContractStages().get(0);

        stage.setName("Test");
        stageRep.save(stage);

        ContractStage updatedStage = contract.getContractStages().get(0);
        Assertions.assertThat(updatedStage.getName()).isEqualTo("Test");
    }
}
