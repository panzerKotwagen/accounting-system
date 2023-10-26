package ru.kotb.accounting_system.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.service.OrganisationService;


/**
 * The controller class that processes requests to /api/organisations.
 */
@RestController
@RequestMapping("/api/organisations")
public class OrganisationController extends AbstractController<Organisation, OrganisationService> {


    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    public OrganisationController(OrganisationService service) {
        super(service);
    }
}
