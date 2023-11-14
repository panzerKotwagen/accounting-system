package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * The class that describes a contract with a counterparty company.
 */
@Data
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
    @NotNull
    @JsonIgnore
    private Contract contract;

    /**
     * The counterparty with which the contract was concluded.
     */
    @NotNull(message = "The field cannot be empty")
    @ManyToOne()
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
