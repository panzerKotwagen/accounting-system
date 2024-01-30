package ru.kotb.accountingsystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accountingsystem.entity.CounterpartyContract;
import ru.kotb.accountingsystem.entity.Organisation;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class CounterpartyRepositoryTest {

    private final CounterpartyContractRepository contractRep;

    private final OrganisationRepository orgRep;

    private Organisation org;

    private CounterpartyContract contract;

    @Autowired
    public CounterpartyRepositoryTest(CounterpartyContractRepository contractRep, OrganisationRepository orgRep) {
        this.contractRep = contractRep;
        this.orgRep = orgRep;
    }


    @BeforeEach
    public void init()  {
        org = new Organisation();
        org = orgRep.save(org);

        contract = new CounterpartyContract();
        contract.setOrganisation(org);
        contract = contractRep.save(contract);
        org.getContracts().add(contract);
    }

    @Test
    public void deleteOrganisationDeletesContracts() {
        orgRep.deleteById(org.getId());

        Assertions.assertThat(contractRep.findById(contract.getId())).isEmpty();
    }

    @Test
    public void updateOrganisation() {
        org.setName("Test");

        orgRep.save(org);

        Assertions.assertThat(contract.getOrganisation().getName())
                .isEqualTo("Test");
    }

    @Test
    public void deleteContractDontDeleteOrganisation() {
        contractRep.deleteById(contract.getId());

        Assertions.assertThat(orgRep.findById(org.getId())).isNotEmpty();
        Assertions.assertThat(contractRep.findById(contract.getId())).isEmpty();
    }
}
