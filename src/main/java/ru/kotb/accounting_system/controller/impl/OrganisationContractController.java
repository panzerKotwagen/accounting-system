package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.OrganisationContractService;

import java.util.List;


/**
 * The Controller class that processes requests to /api/organisationContracts.
 */
@RestController
@RequestMapping("/api/organisation-contracts")
public class OrganisationContractController
        extends AbstractController<OrganisationContract, OrganisationContractService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the entity service bean
     */
    public OrganisationContractController(OrganisationContractService service) {
        super(service);
    }
}
