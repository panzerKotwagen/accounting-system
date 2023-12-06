package ru.kotb.accounting_system.dto;

import lombok.Getter;
import ru.kotb.accounting_system.entity.AbstractContract;
import ru.kotb.accounting_system.entity.Contract;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ContractDTO extends AbstractContract {

    /**
     * The contract type.
     */
    private String type;

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
        type = "Main";
        setContractType(contract.getContractType());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
        setAmount(contract.getAmount());
    }

    /**
     * Constructs DTO from the {@code CounterpartyContract} entity.
     */
    public ContractDTO(CounterpartyContract contract) {
        setName(contract.getName());
        type = "Counterparty";
        setContractType(contract.getContractType());
        setActualStartDate(contract.getActualStartDate());
        setActualEndDate(contract.getActualEndDate());
        setPlannedStartDate(contract.getPlannedStartDate());
        setPlannedEndDate(contract.getPlannedEndDate());
        setAmount(contract.getAmount());
        mainContractName = contract.getContract().getName();
    }

    /**
     * Returns the list with values of object attributes in String
     * format.
     */
    public List<String> getAttributesAsStringList() {
        List<String> list = new ArrayList<>();
        list.add(getName());
        list.add(getType().toString());
        list.add(getContractType().toString());
        list.add(getPlannedStartDate().toString());
        list.add(getActualStartDate().toString());
        list.add(getPlannedEndDate().toString());
        list.add(getActualEndDate().toString());
        list.add(String.valueOf(getAmount()));
        list.add(getMainContractName());
        return list;
    }
}
