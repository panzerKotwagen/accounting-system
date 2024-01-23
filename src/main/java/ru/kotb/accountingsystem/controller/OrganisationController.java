package ru.kotb.accountingsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accountingsystem.entity.Organisation;
import ru.kotb.accountingsystem.modelassembler.CommonModelAssembler;
import ru.kotb.accountingsystem.service.CommonService;


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
