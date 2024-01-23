package ru.kotb.accountingsystem.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kotb.accountingsystem.entity.AbstractContract;
import ru.kotb.accountingsystem.entity.Contract;
import ru.kotb.accountingsystem.entity.CounterpartyContract;


@Getter
@Setter
public class ContractDTO extends AbstractContract {

    /**
     * The contract type.
     */
    private ContractType type;

    /**
     * The name of the contract that the contract with counterparties
     * is associated with.
     */
    private String mainContractName;

    /**
     * Constructs DTO from the {@code Contract} entity.
     */
    public ContractDTO(Contract contract) {
        setName(contract.getName());
        type = ContractType.MAIN_CONTRACT;
        setKindOfWork(contract.getKindOfWork());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
        setAmount(contract.getAmount());
        setMainContractName("");
    }

    /**
     * Constructs DTO from the {@code CounterpartyContract} entity.
     */
    public ContractDTO(CounterpartyContract contract) {
        setName(contract.getName());
        type = ContractType.COUNTERPARTY_CONTRACT;
        setKindOfWork(contract.getKindOfWork());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
        setAmount(contract.getAmount());
        mainContractName = contract.getContract().getName();
    }
}
