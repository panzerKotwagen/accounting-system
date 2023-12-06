package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kotb.accounting_system.entity.group.ContractNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


/**
 * The class that describes a contract with a counterparty company.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organisation_contracts")
public class CounterpartyContract extends AbstractContract {

    /**
     * The contract that the counterparty contract is associated with.
     */
    @ManyToOne()
    @JoinColumn(name = "contract_id")
    @Null(groups={ContractNull.class})
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Contract contract;

    /**
     * The counterparty with which the contract was concluded.
     */
    @NotNull(message = "The field cannot be empty")
    @ManyToOne()
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
