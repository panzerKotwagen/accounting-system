package ru.kotb.accounting_system.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.configuration.TestConfig;
import ru.kotb.accounting_system.entity.Contract;

import java.sql.Date;
import java.util.List;


@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class ContractDAOTest {

    @Autowired
    ContractDAO contractDAO;

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

        contractDAO.save(contract1);
        contractDAO.save(contract2);
        contractDAO.save(contract3);

        Date start = Date.valueOf("2023-12-05");
        Date end = Date.valueOf("2023-12-30");
        List<Contract> list = contractDAO.findAllWhereDateBetween(start, end);

        Assertions.assertThat(list.size()).isEqualTo(1);
    }
}
