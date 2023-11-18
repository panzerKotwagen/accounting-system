package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


/**
 * The class that describes the contract that is concluded between
 * the company and the customer.
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "contracts")
public class Contract extends AbstractContract {

    /**
     * The stages of the contract.
     */
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "contract")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ContractStage> contractStages;

    /**
     * The contract with the counterparty that included into the contract.
     */
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "contract")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CounterpartyContract> counterpartyContracts;

    public Contract() {
        counterpartyContracts = new ArrayList<>();
        contractStages = new ArrayList<>();
    }
}
