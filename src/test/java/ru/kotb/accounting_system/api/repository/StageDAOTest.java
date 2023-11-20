package ru.kotb.accounting_system.api.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.dao.StageDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;

import java.util.List;


@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@Transactional
public class StageDAOTest {

    @Autowired
    private StageDAO stageDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Test
    public void findAllByContractIdReturnStages() {
        Contract contract = new Contract();
        ContractStage stage1 = new ContractStage();
        ContractStage stage2 = new ContractStage();

        stage1.setContract(contract);
        stage2.setContract(contract);

        contract.getContractStages().add(stage1);
        contract.getContractStages().add(stage2);
        contract = contractDAO.save(contract);

        List<ContractStage> stages = stageDAO.findAllByContractId(contract.getId());

        Assertions.assertThat(stages.size()).isEqualTo(2);
    }
}
