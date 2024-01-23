package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.dao.CommonDAO;
import ru.kotb.accountingsystem.entity.ContractStage;


/**
 * The service for working with {@code ContractStage} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractStageService
        extends AbstractService<ContractStage, CommonDAO<ContractStage>> {

    @Autowired
    public ContractStageService(CommonDAO<ContractStage> contractStageDAO) {
        super(contractStageDAO);
        contractStageDAO.setClass(ContractStage.class);
    }
}
