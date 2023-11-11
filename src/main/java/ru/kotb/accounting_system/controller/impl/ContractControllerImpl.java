package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.ContractController;
import ru.kotb.accounting_system.dto.DatePeriodDTO;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.model_assembler.ContractModelAssembler;
import ru.kotb.accounting_system.model_assembler.ContractStageModelAssembler;
import ru.kotb.accounting_system.model_assembler.CounterpartyContractModelAssembler;
import ru.kotb.accounting_system.service.ContractService;


/**
 * The controller class that processes requests to /api/contracts.
 */
@RestController
public class ContractControllerImpl
        extends AbstractController<Contract, ContractService>
        implements ContractController {

    private final ContractStageModelAssembler stageAssembler;

    private final CounterpartyContractModelAssembler counterpartyAssembler;

    /**
     * Constructs the controller and links it with the beans.
     *
     * @param service the contract service bean
     * @param assembler the contract assembler bean
     * @param stageAssembler the stage assembler bean
     * @param counterpartyAssembler the counterparty assembler bean
     */
    @Autowired
    public ContractControllerImpl(
            ContractService service,
            ContractModelAssembler assembler,
            ContractStageModelAssembler stageAssembler,
            CounterpartyContractModelAssembler counterpartyAssembler) {

        super(service, assembler);
        this.stageAssembler = stageAssembler;
        this.counterpartyAssembler = counterpartyAssembler;
    }

    /**
     * Returns the list of all stages of the contract with the
     * specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @Override
    public CollectionModel<EntityModel<ContractStage>> showAllContractStages(int contractId) {
        return stageAssembler.toCollectionModel(service.getAllStages(contractId));
    }

    /**
     * Returns the list of all organisation contract of the contract
     * with the specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @Override
    public CollectionModel<EntityModel<CounterpartyContract>>
    showAllOrganisationContracts(int contractId) {

        return counterpartyAssembler.toCollectionModel(
                service.getAllOrganisationContracts(contractId));
    }

    /**
     * Returns the Excel file with the all contracts.
     *
     * @return the Excel file with the all contracts
     */
    @Override
    public ResponseEntity<Resource> downloadContractsReport() {
        String filename = "contracts.xlsx";
        InputStreamResource file = new InputStreamResource(
                service.getContractsReport());

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    /**
     * Return the Excel file with the all stages of the specified
     * contract.
     *
     * @param contractId the id of the contract
     * @return the Excel file with the all stages of the specified
     * contract
     */
    @Override
    public ResponseEntity<Resource> downloadStagesReport(
            @PathVariable("id") int contractId) {
        String filename = "contract_stages.xlsx";
        InputStreamResource file = new InputStreamResource(service
                .getStagesReport(contractId));

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    /**
     * Returns all contracts for the specified period, or just all
     * contracts if it was not specified.
     *
     * @param periodDTO DTO with start and end date of the period
     * @return JSON array with all contracts in the table
     */
    @Override
    public CollectionModel<EntityModel<Contract>> showAll(
            @RequestBody(required = false) DatePeriodDTO periodDTO) {

        if (periodDTO == null)
            return assembler.toCollectionModel(service.getAll());

        return assembler.toCollectionModel(
                service.getForPeriod(periodDTO.getStart(), periodDTO.getEnd()));
    }
}
