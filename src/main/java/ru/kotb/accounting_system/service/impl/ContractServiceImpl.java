package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.service.ContractService;


/**
 * The implementation of the ContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ContractServiceImpl extends AbstractService<Contract, ContractDAO> implements ContractService {

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO) {
        super(contractDAO);
        contractDAO.setClass(Contract.class);
    }
}
