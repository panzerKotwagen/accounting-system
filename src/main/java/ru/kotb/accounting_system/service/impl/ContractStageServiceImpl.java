package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.ContractStageDAO;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.service.ContractStageService;


/**
 * The implementation of the ContractStageService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractStageServiceImpl
        extends AbstractService<ContractStage, ContractStageDAO>
        implements ContractStageService {

    @Autowired
    public ContractStageServiceImpl(ContractStageDAO contractStageDAO) {
        super(contractStageDAO);
        contractStageDAO.setClass(ContractStage.class);
    }
}
