package ru.kotb.accounting_system.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.entity.Organisation;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class CounterpartyDAOTest {

    private final CommonDAO<CounterpartyContract> contractDAO;

    private final CommonDAO<Organisation> orgDAO;

    private Organisation org;

    private CounterpartyContract contract;

    @Autowired
    public CounterpartyDAOTest(CommonDAO<CounterpartyContract> contractDAO, CommonDAO<Organisation> orgDAO) {
        this.contractDAO = contractDAO;
        this.orgDAO = orgDAO;
        contractDAO.setClass(CounterpartyContract.class);
        orgDAO.setClass(Organisation.class);
    }


    @BeforeEach
    public void init()  {
        org = new Organisation();
        org = orgDAO.save(org);

        contract = new CounterpartyContract();
        contract.setOrganisation(org);
        contract = contractDAO.save(contract);
        org.getContracts().add(contract);
    }

    @Test
    public void deleteOrganisationDeletesContracts() {
        orgDAO.deleteById(org.getId());

        Assertions.assertThat(contractDAO.findById(contract.getId())).isEmpty();
    }

    @Test
    public void updateOrganisation() {
        org.setName("Test");

        orgDAO.save(org);

        Assertions.assertThat(contract.getOrganisation().getName())
                .isEqualTo("Test");
    }

    @Test
    public void deleteContractDontDeleteOrganisation() {
        contractDAO.deleteById(contract.getId());

        Assertions.assertThat(orgDAO.findById(org.getId())).isNotEmpty();
        Assertions.assertThat(contractDAO.findById(contract.getId())).isEmpty();
    }
}
