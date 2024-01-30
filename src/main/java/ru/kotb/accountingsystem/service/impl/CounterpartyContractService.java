package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.entity.CounterpartyContract;
import ru.kotb.accountingsystem.repository.CounterpartyContractRepository;


/**
 * The service for working with {@code CounterpartyContract} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class CounterpartyContractService
        extends AbstractService<CounterpartyContract,
        CounterpartyContractRepository> {

    @Autowired
    public CounterpartyContractService(CounterpartyContractRepository counterpartyRep) {
        super(counterpartyRep);
    }
}
