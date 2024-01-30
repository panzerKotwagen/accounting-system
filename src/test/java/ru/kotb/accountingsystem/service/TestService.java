package ru.kotb.accountingsystem.service;

import ru.kotb.accountingsystem.entity.TestEntity;
import ru.kotb.accountingsystem.repository.TestEntityRepository;
import ru.kotb.accountingsystem.service.impl.AbstractService;


public class TestService
        extends AbstractService<TestEntity, TestEntityRepository> {

    /**
     * Links the specified DAO with the service.
     *
     * @param testRep the DAO of the specified entity class
     */
    public TestService(TestEntityRepository testRep) {
        super(testRep);
    }
}
