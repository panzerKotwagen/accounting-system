package ru.kotb.accounting_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The class that describes the contract stage.
 */
@Entity
@Table(name = "contract_stages")
public class ContractStage extends AbstractEntity {

    /**
     * The contract stage name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The planned date when does the contract enter into force.
     */
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
    @Column(name = "amount")
    private int amount;

    /**
     * The planned material cost.
     */
    @Column(name = "planned_material_cost")
    private int plannedMaterialCost;

    /**
     * The actual material cost.
     */
    @Column(name = "actual_material_cost")
    private int actualMaterialCost;

    /**
     * The planned salary cost.
     */
    @Column(name = "planned_salary_cost")
    private int plannedSalaryCost;

    /**
     * The actual salary cost.
     */
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
