package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.CommonService;


/**
 * The controller class that processes requests to /api/contract-stages.
 */
@RestController
@RequestMapping("/api/contract-stages")
public class ContractStageController
        extends AbstractController<ContractStage, CommonService<ContractStage>> {

    @Autowired
    public ContractStageController(
            CommonService<ContractStage> service,
            CommonModelAssembler<ContractStage> assembler) {
        super(service, assembler);
    }
}
