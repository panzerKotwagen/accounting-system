package ru.kotb.accountingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accountingsystem.entity.ContractStage;
import ru.kotb.accountingsystem.modelassembler.CommonModelAssembler;
import ru.kotb.accountingsystem.service.CommonService;


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
