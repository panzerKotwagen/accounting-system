package ru.kotb.accounting_system.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.OrganisationController;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.model_assembler.impl.OrganisationModelAssemblerImpl;
import ru.kotb.accounting_system.service.OrganisationService;


/**
 * The controller class that processes requests to /api/organisations.
 */
@RestController
public class OrganisationControllerImpl
        extends AbstractController<Organisation, OrganisationService>
        implements OrganisationController {


    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    public OrganisationControllerImpl(
            OrganisationService service,
            OrganisationModelAssemblerImpl assembler) {

        super(service, assembler);
    }
}
