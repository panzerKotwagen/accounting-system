package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.ICommonDAO;
import ru.kotb.accounting_system.entity.CounterpartyContract;


/**
 * The implementation of the OrganisationContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class CounterpartyContractService
        extends AbstractService<CounterpartyContract,
        ICommonDAO<CounterpartyContract>> {

    @Autowired
    public CounterpartyContractService(ICommonDAO<CounterpartyContract> counterpartyContractDAO) {
        super(counterpartyContractDAO);
        counterpartyContractDAO.setClass(CounterpartyContract.class);
    }
}
