package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.ContractStage;
import ru.kotb.accounting_system.entity.CounterpartyContract;
import ru.kotb.accounting_system.exception_handling.NoSuchEntityException;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.model_assembler.ContractModelAssembler;
import ru.kotb.accounting_system.model_assembler.ContractStageModelAssembler;
import ru.kotb.accounting_system.model_assembler.CounterpartyContractModelAssembler;
import ru.kotb.accounting_system.service.ContractService;

import java.sql.Date;
import java.util.Optional;


/**
 * The controller class that processes requests to /api/contracts.
 */
@RestController
@RequestMapping("/api/contracts")
public class ContractController
        extends AbstractController<Contract, ContractService> {

    private final CommonModelAssembler<ContractStage> stageAssembler;

    private final CommonModelAssembler<CounterpartyContract> counterpartyAssembler;

    private final ContractStageController stageController;

    private final CounterpartyContractController counterpartyContractController;

    /**
     * Constructs the controller and links it with the beans.
     *
     * @param service               the contract service bean
     * @param assembler             the contract assembler bean
     * @param stageAssembler        the stage assembler bean
     * @param counterpartyAssembler the counterparty assembler bean
     */
    @Autowired
    public ContractController(
            ContractService service,
            ContractModelAssembler assembler,
            ContractStageModelAssembler stageAssembler,
            CounterpartyContractModelAssembler counterpartyAssembler,
            ContractStageController stageController,
            CounterpartyContractController counterpartyContractController) {

        super(service, assembler);
        this.stageAssembler = stageAssembler;
        this.counterpartyAssembler = counterpartyAssembler;
        this.stageController = stageController;
        this.counterpartyContractController = counterpartyContractController;
    }

    /**
     * Returns all contracts for the specified period, or just all
     * contracts if it was not specified.
     *
     * @param startDate DTO with start and end date of the period
     * @return JSON array with all contracts in the table
     */
    @GetMapping(params = {"startDate", "endDate"})
    public CollectionModel<EntityModel<Contract>> all(
            @RequestParam(value = "startDate", required = false) Optional<Date> startDate,
            @RequestParam(value = "endDate", required = false) Optional<Date> endDate) {

        if (startDate.isPresent() && endDate.isPresent()) {
            return assembler.toCollectionModel(
                    service.getAll(startDate.get(), endDate.get()));
        }

        return assembler.toCollectionModel(service.getAll());
    }

    /**
     * Returns the list of all stages of the contract with the
     * specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/stages")
    public CollectionModel<EntityModel<ContractStage>> allStages(
            @PathVariable("id") int contractId) {

        return stageAssembler.toCollectionModel(service.getAllStages(contractId));
    }

    /**
     * Returns the list of all organisation contract of the contract
     * with the specified ID as JSON array.
     *
     * @param contractId the specified ID of the contract
     * @return list of all stages of the contract
     */
    @GetMapping("/{id}/counterparty-contracts")
    public CollectionModel<EntityModel<CounterpartyContract>> allCounterpartyContracts(
            @PathVariable("id") int contractId) {

        return counterpartyAssembler.toCollectionModel(
                service.getAllOrganisationContracts(contractId));
    }

    /**
     * Adds a stage to the contract with specified ID. The request
     * body must have got the JSON object with the data of the new stage.
     *
     * @param contractId id of the contract
     * @param stage      the {@code ContractStage} entity
     * @return HTTP 201 response with added stage
     */
    @PostMapping("/{id}/stages")
    public EntityModel<ContractStage> addStage(
            @PathVariable("id") int contractId, @RequestBody ContractStage stage) {

        stage = service.addStage(contractId, stage);
        return stageAssembler.toModel(stage);
    }

    /**
     * Updates the stage to the contract with specified ID. The request
     * body must have got the JSON object with the data of the existed stage.
     *
     * @param contractId id of the contract
     * @param stage      the {@code ContractStage} entity
     * @return HTTP 201 response with updated stage
     */
    @PutMapping("/{id}/stages")
    public ResponseEntity<?> updateStage(
            @PathVariable("id") int contractId, @RequestBody ContractStage stage) {

        Contract contract = service.getById(contractId);

        stage.setContract(contract);
        return stageController.update(stage);
    }

    /**
     * Adds a counterparty contract to the contract with specified ID.
     * The request body must have got the JSON object with the data of
     * the new counterparty contract.
     *
     * @param contractId           id of the contract
     * @param counterpartyContract the {@code CounterpartyContract} entity
     * @return HTTP 201 response with added counterparty contract
     */
    @PostMapping("/{id}/counterparty-contracts")
    public EntityModel<CounterpartyContract> addCounterparty(
            @PathVariable("id") int contractId,
            @RequestBody CounterpartyContract counterpartyContract) {

        counterpartyContract = service.addCounterpartyContract(
                contractId, counterpartyContract);

        return counterpartyAssembler.toModel(counterpartyContract);
    }

    /**
     * Updates the counterparty contract in the contract with specified ID.
     * The request body must have got the JSON object with the data of
     * the existed counterparty contract.
     *
     * @param contractId           id of the contract
     * @param counterpartyContract the {@code CounterpartyContract} entity
     * @return HTTP 201 response with added counterparty contract
     */
    @PutMapping("/{id}/counterparty-contracts")
    public ResponseEntity<?> updateCounterparty(
            @PathVariable("id") int contractId,
            @RequestBody CounterpartyContract counterpartyContract) {

        Contract contract = service.getById(contractId);

        counterpartyContract.setContract(contract);
        return counterpartyContractController.update(counterpartyContract);
    }

    /**
     * Returns the Excel file with the all contracts.
     *
     * @return the Excel file with the all contracts
     */
    @GetMapping("/report/download")
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
    @GetMapping("{id}/stages/report/download")
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
}
