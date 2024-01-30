package ru.kotb.accountingsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.kotb.accountingsystem.repository.CommonDAO;
import ru.kotb.accountingsystem.repository.ContractDAO;
import ru.kotb.accountingsystem.repository.StageDAO;
import ru.kotb.accountingsystem.repository.UserDAO;

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
    public UserDAO userDAO() {
        return new UserDAO(entityManager);
    }
}
