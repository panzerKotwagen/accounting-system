package ru.kotb.accounting_system.dto;

import lombok.Getter;
import ru.kotb.accounting_system.entity.AbstractContract;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.CounterpartyContract;


@Getter
public class ContractDTO extends AbstractContract {

    /**
     * The contract type.
     */
    private Type type;

    /**
     * The name of the contract that the contract with counterparties
     * is associated with.
     */
    private String mainContractName;

    /**
     * Constructs DTO from the {@code Contract} entity.
     */
    public ContractDTO(Contract contract) {
        type = Type.MAIN_CONTRACT;
        setName(contract.getName());
        setContractType(contract.getContractType());
        setAmount(contract.getAmount());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
    }

    /**
     * Constructs DTO from the {@code CounterpartyContract} entity.
     */
    public ContractDTO(CounterpartyContract contract) {
        type = Type.COUNTERPARTY_CONTRACT;
        setName(contract.getName());
        setContractType(contract.getContractType());
        setAmount(contract.getAmount());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
        mainContractName = contract.getContract().getName();
    }
}
