package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.CommonService;


/**
 * The controller class that processes requests to /api/organisations.
 */
@RestController
@RequestMapping("/api/organisations")
public class OrganisationController
        extends AbstractController<Organisation, CommonService<Organisation>> {

    public OrganisationController(
            CommonService<Organisation> service,
            CommonModelAssembler<Organisation> assembler) {

        super(service, assembler);
    }
}
