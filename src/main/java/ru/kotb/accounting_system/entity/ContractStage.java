package ru.kotb.accounting_system.entity;

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

    public ContractStage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(String plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(String plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPlannedMaterialCost() {
        return plannedMaterialCost;
    }

    public void setPlannedMaterialCost(int plannedMaterialCost) {
        this.plannedMaterialCost = plannedMaterialCost;
    }

    public int getActualMaterialCost() {
        return actualMaterialCost;
    }

    public void setActualMaterialCost(int actualMaterialCost) {
        this.actualMaterialCost = actualMaterialCost;
    }

    public int getPlannedSalaryCost() {
        return plannedSalaryCost;
    }

    public void setPlannedSalaryCost(int plannedSalaryCost) {
        this.plannedSalaryCost = plannedSalaryCost;
    }

    public int getActualSalaryCost() {
        return actualSalaryCost;
    }

    public void setActualSalaryCost(int actualSalaryCost) {
        this.actualSalaryCost = actualSalaryCost;
    }
}
