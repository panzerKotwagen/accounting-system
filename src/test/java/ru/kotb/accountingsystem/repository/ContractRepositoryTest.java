package ru.kotb.accountingsystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.configuration.TestConfig;
import ru.kotb.accountingsystem.entity.Contract;

import java.sql.Date;
import java.util.List;


@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class ContractRepositoryTest {

    @Autowired
    ContractRepository contractRep;

    @Test
    void findAllWhereDateBetween() {
        Contract contract1 = new Contract();
        Contract contract2 = new Contract();
        Contract contract3 = new Contract();
        contract1.setPlannedStartDate(Date.valueOf("1812-12-12"));
        contract1.setPlannedEndDate(Date.valueOf("1813-12-13"));
        contract2.setPlannedStartDate(Date.valueOf("2023-12-05"));
        contract2.setPlannedEndDate(Date.valueOf("2023-12-31"));
        contract3.setPlannedStartDate(Date.valueOf("2023-12-05"));
        contract3.setPlannedEndDate(Date.valueOf("2023-12-30"));

        contractRep.save(contract1);
        contractRep.save(contract2);
        contractRep.save(contract3);

        Date start = Date.valueOf("2023-12-05");
        Date end = Date.valueOf("2023-12-30");
        List<Contract> list = contractRep.findByPlannedStartDateGreaterThanEqualAndPlannedEndDateLessThanEqual(start, end);

        Assertions.assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void findWhereDateGetsBoundaryValues() {
        Contract contract1 = new Contract();

        contract1.setPlannedStartDate(Date.valueOf("1812-12-12"));
        contract1.setPlannedEndDate(Date.valueOf("1813-12-13"));

        contractRep.save(contract1);

        Date start = Date.valueOf("1812-12-12");
        Date end = Date.valueOf("1813-12-13");
        List<Contract> list = contractRep.findByPlannedStartDateGreaterThanEqualAndPlannedEndDateLessThanEqual(start, end);

        Assertions.assertThat(list.size()).isEqualTo(1);
    }
}
