package ru.kotb.accountingsystem.service;

import ru.kotb.accountingsystem.entity.TestEntity;
import ru.kotb.accountingsystem.dao.CommonDAO;
import ru.kotb.accountingsystem.service.impl.AbstractServiceOld;


public class TestService
        extends AbstractServiceOld<TestEntity, CommonDAO<TestEntity>> {

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
