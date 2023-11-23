package ru.kotb.accounting_system.api.service;

import ru.kotb.accounting_system.api.entity.TestEntity;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.service.impl.AbstractService;


public class TestService
        extends AbstractService<TestEntity, CommonDAO<TestEntity>> {

    /**
     * Links the specified DAO with the service.
     *
     * @param genericDAOImpl the DAO of the specified entity class
     */
    public TestService(CommonDAO<TestEntity> genericDAOImpl) {
        super(genericDAOImpl);
        genericDAOImpl.setClass(TestEntity.class);
    }
}
