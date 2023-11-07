package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.CounterpartyContractDAO;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.service.CounterpartyContractService;


/**
 * The implementation of the OrganisationContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class CounterpartyContractServiceImpl
        extends AbstractService<CounterpartyContract, CounterpartyContractDAO>
        implements CounterpartyContractService {

    @Autowired
    public CounterpartyContractServiceImpl(CounterpartyContractDAO counterpartyContractDAO) {
        super(counterpartyContractDAO);
        counterpartyContractDAO.setClass(CounterpartyContract.class);
    }
}
