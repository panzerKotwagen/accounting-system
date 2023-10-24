package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.service.OrganisationService;

import java.util.List;


/**
 * The Controller class that processes requests to /api/organisations.
 */
@RestController
@RequestMapping("/api")
public class OrganisationController {

    /**
     * The OrganisationService bean for working with the
     * organisations.
     */
    private final OrganisationService organisationService;

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param organisationService the organisation service bean
     */
    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    //TODO: rewrite comment
    /**
     * Returns a JSON object with all organisations in the table.
     *
     * @return JSON object with all organisations in the table
     */
    @GetMapping("/organisations")
    public List<Organisation> showAllOrganisations() {
        return organisationService.getAll();
    }

    //TODO: rewrite comment
    /**
     * Returns a JSON object with description of the specified organization.
     *
     * @param organisationId the specified ID of the organisation
     * @return the JSON object with the specified organisation
     */
    @GetMapping("/organisations/{id}")
    public Organisation getOrganisation(@PathVariable("id") int organisationId) {
        return organisationService.get(organisationId);
    }

    //TODO: rewrite comment
    /**
     * Accepts the JSON object with the description of the
     * organisation, transforms it and saves into the table.
     *
     * @param organisation JSON object with the description of the
     *                     organisation
     * @return accepted JSON object
     */
    @PostMapping("/organisations")
    public Organisation addOrganisation(@RequestBody Organisation organisation) {
        organisationService.saveOrUpdate(organisation);
        return organisation;
    }

    /**
     * Updates the existing organisation in the table. The request
     * body must has got the JSON object with the ID of an existing
     * entity and the updated data.
     *
     * @param organisation entity object with ID
     * @return saved object
     */
    @PutMapping("/organisations")
    public Organisation updateOrganisation(@RequestBody Organisation organisation) {
        organisationService.saveOrUpdate(organisation);
        return organisation;
    }

    //TODO: rewrite comment
    /**
     * Deletes the organisation with the specified ID and return the
     * informative message.
     *
     * @param organisationId specified ID of the organisation
     * @return operation status message
     */
    @DeleteMapping("/organisations/{id}")
    public String deleteUser(@PathVariable("id") int organisationId) {
        organisationService.delete(organisationId);
        return "Organisation with ID = " + organisationId + " was deleted.";
    }
}
