package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.entity.CounterpartyContract;


/**
 * The service for working with {@code CounterpartyContract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class CounterpartyContractService
        extends AbstractService<CounterpartyContract,
        CommonDAO<CounterpartyContract>> {

    @Autowired
    public CounterpartyContractService(CommonDAO<CounterpartyContract> counterpartyContractDAO) {
        super(counterpartyContractDAO);
        counterpartyContractDAO.setClass(CounterpartyContract.class);
    }
}
