package ru.kotb.accounting_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


/**
 * The class that describes the contract stage.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "contract_stages")
public class ContractStage extends AbstractEntity {

    /**
     * The contract stage name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=100, message = "The maximum field length is limited to 150 characters")
    @Column(name = "name")
    private String name;

    /**
     * The planned date when does the contract enter into force.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_start_date")
    private String plannedStartDate;

    /**
     * The actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    private String actualStartDate;

    /**
     * The planned date when does the contract end.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_end_date")
    private String plannedEndDate;

    /**
     * The actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    private String actualEndDate;

    /**
     * The stage amount.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "amount")
    private int amount;

    /**
     * The planned material cost.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "planned_material_cost")
    private int plannedMaterialCost;

    /**
     * The actual material cost.
     */
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "actual_material_cost")
    private int actualMaterialCost;

    /**
     * The planned salary cost.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "planned_salary_cost")
    private int plannedSalaryCost;

    /**
     * The actual salary cost.
     */
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "actual_salary_cost")
    private int actualSalaryCost;
}
