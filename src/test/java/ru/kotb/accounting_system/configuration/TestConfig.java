package ru.kotb.accounting_system.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.dao.StageDAO;
import ru.kotb.accounting_system.dao.UserDAO;

import javax.persistence.EntityManager;


@TestConfiguration
@Import(CommonDAO.class)
public class TestConfig {

    @Autowired
    EntityManager entityManager;

    @Bean
    public StageDAO stageDAO() {
        return new StageDAO(entityManager);
    }

    @Bean
    public ContractDAO contractDAO() {
        return new ContractDAO(entityManager);
    }

    @Bean
    public RoleDAO roleDAO() {
        return new RoleDAO(entityManager);
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(entityManager);
    }
}
