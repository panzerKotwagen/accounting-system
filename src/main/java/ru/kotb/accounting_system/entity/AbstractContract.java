package ru.kotb.accounting_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;


/**
 * The class that describes common fields of contract.
 */
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class AbstractContract extends AbstractEntity {

    public enum ContractType {
        SHIPMENT("Shipment"),
        PURCHASE("Purchase"),
        WORK("Work");

        private final String text;

        ContractType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * The contract name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=100, message = "The maximum field length is limited to 100 characters")
    @Column(name = "name")
    protected String name;

    /**
     * The contract amount.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "amount")
    protected int amount;

    /**
     * The type of the contract.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name="contract_type")
    @Enumerated(EnumType.STRING)
    protected ContractType contractType;

    /**
     * Expected date when does the contract enter into force.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_start_date")
    @FutureOrPresent
    protected Date plannedStartDate;

    /**
     * Actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    @FutureOrPresent
    protected Date actualStartDate;

    /**
     * Expected date when does the contract end.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_end_date")
    @FutureOrPresent
    protected Date plannedEndDate;

    /**
     * Actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    @FutureOrPresent
    protected Date actualEndDate;

    public AbstractContract(String name, int amount, ContractType contractType, Date plannedStartDate, Date plannedEndDate) {
        this.name = name;
        this.amount = amount;
        this.contractType = contractType;
        this.plannedStartDate = plannedStartDate;
        this.plannedEndDate = plannedEndDate;
    }
}
