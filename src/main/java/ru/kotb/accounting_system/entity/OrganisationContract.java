package ru.kotb.accounting_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;


/**
 * The class that describes a contract with a counterparty company.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "organisation_contracts")
public class OrganisationContract extends AbstractEntity {

    /**
     * The contract name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=100, message = "The maximum field length is limited to 100 characters")
    @Column(name = "name")
    private String name;

    /**
     * The type of the contract.
     */
    @NotNull(message = "The field cannot be empty")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    /**
     * The counterparty with which the contract was concluded.
     */
    @NotNull(message = "The field cannot be empty")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    /**
     * The contract amount.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "amount")
    private int amount;

    /**
     * Expected date when does the contract enter into force.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_start_date")
    @FutureOrPresent
    private Date plannedStartDate;

    /**
     * Expected date when does the contract end.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_end_date")
    @FutureOrPresent
    private Date plannedEndDate;

    /**
     * Actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    @FutureOrPresent
    private Date actualStartDate;

    /**
     * Actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    @FutureOrPresent
    private Date actualEndDate;
}
