package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contracts")
public class Contract extends AbstractContract {

    /**
     * The stages of the contract.
     */
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REMOVE}, mappedBy = "contract")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ContractStage> contractStages = new ArrayList<>();

    /**
     * The contract with the counterparty that included into the contract.
     */
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "contract", orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CounterpartyContract> counterpartyContracts = new ArrayList<>();
}
