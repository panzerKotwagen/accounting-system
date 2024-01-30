package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.entity.ContractStage;
import ru.kotb.accountingsystem.repository.StageRepository;


/**
 * The service for working with {@code ContractStage} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractStageService
        extends AbstractService<ContractStage, StageRepository> {

    @Autowired
    public ContractStageService(StageRepository stageRep) {
        super(stageRep);
    }
}
