package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.entity.ContractStage;


/**
 * The implementation of the ContractStageService interface.
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
