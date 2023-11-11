package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.entity.ContractStage;


/**
 * The controller class that processes requests to /api/contract-stages.
 */
@RequestMapping("/api/contract-stages")
public interface ContractStageController extends CommonController<ContractStage> {
}
