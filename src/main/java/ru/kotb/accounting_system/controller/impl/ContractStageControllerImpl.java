package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.ContractStageController;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.model_assembler.ContractStageModelAssembler;
import ru.kotb.accounting_system.service.ContractStageService;


/**
 * The controller class that processes requests to /api/contract-stages.
 */
@RestController
public class ContractStageControllerImpl
        extends AbstractController<ContractStage, ContractStageService>
        implements ContractStageController {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the contract stage service bean
     */
    @Autowired
    public ContractStageControllerImpl(
            ContractStageService service,
            ContractStageModelAssembler assembler) {
        super(service, assembler);
    }
}
