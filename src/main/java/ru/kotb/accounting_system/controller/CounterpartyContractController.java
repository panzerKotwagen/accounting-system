package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.entity.CounterpartyContract;


/**
 * The controller class that processes requests to /api/counterparty-contracts.
 */
@RequestMapping("/api/counterparty-contracts")
public interface CounterpartyContractController
        extends CommonController<CounterpartyContract> {
}
