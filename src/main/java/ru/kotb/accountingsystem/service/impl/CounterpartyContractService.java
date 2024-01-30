package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.repository.CommonDAO;
import ru.kotb.accountingsystem.entity.CounterpartyContract;


/**
 * The service for working with {@code CounterpartyContract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class CounterpartyContractService
        extends AbstractServiceOld<CounterpartyContract,
                CommonDAO<CounterpartyContract>> {

    @Autowired
    public CounterpartyContractService(CommonDAO<CounterpartyContract> counterpartyContractDAO) {
        super(counterpartyContractDAO);
        counterpartyContractDAO.setClass(CounterpartyContract.class);
    }
}
