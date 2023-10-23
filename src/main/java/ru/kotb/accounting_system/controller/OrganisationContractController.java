package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.OrganisationContractService;

import java.util.List;


/**
 * The Controller class that processes requests to /api/organisationContracts.
 */
@RestController
@RequestMapping("/api")
public class OrganisationContractController {

    /**
     * The OrganisationContractService object for working with the
     * organisation contracts.
     */
    private final OrganisationContractService organisationContractService;

    @Autowired
    public OrganisationContractController(OrganisationContractService organisationContractService) {
        this.organisationContractService = organisationContractService;
    }

    /**
     * Returns a JSON object with all organisation contracts in the table.
     *
     * @return JSON object with all contracts in the table
     */
    @GetMapping("/organisationContracts")
    public List<OrganisationContract> showAllOrganisationContracts() {
        return organisationContractService.getAllOrganisationContracts();
    }

    /**
     * Returns a JSON object with description of the specified contract.
     *
     * @param organisationContractId the specified ID of the contract
     * @return the JSON object with the specified contract
     */
    @GetMapping("/organisationContracts/{id}")
    public OrganisationContract getOrganisationContract(@PathVariable("id") int organisationContractId) {
        return organisationContractService.getOrganisationContract(organisationContractId);
    }

    /**
     * Accepts the JSON object with the description of the
     * organisation contract, transforms it and saves into the table.
     *
     * @param organisationContract JSON object with the description of the
     *                     contract
     * @return accepted JSON object
     */
    @PostMapping("/organisationContracts")
    public OrganisationContract addOrganisationContract(@RequestBody OrganisationContract organisationContract) {
        organisationContractService.saveOrganisationContract(organisationContract);
        return organisationContract;
    }

    /**
     * Deletes the organisation contract with the specified ID and return the
     * informative message.
     *
     * @param organisationContractId specified ID of the contract
     * @return operation status message
     */
    @DeleteMapping("/organisationContracts/{id}")
    public String deleteUser(@PathVariable("id") int organisationContractId) {
        organisationContractService.deleteOrganisationContract(organisationContractId);
        return "OrganisationContract with ID = " + organisationContractId + " was deleted.";
    }
}
