package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;


/**
 * The class that describes the contract stage.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract_stages")
public class ContractStage extends AbstractEntity {

    /**
     * The contract that the stage is associated with.
     */
    @ManyToOne()
    @JoinColumn(name = "contract_id")
    @NotNull
    @JsonIgnore
    private Contract contract;

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
    @FutureOrPresent
    private Date plannedStartDate;

    /**
     * The actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    @FutureOrPresent
    private Date actualStartDate;

    /**
     * The planned date when does the contract end.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_end_date")
    @FutureOrPresent
    private Date plannedEndDate;

    /**
     * The actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    @FutureOrPresent
    private Date actualEndDate;

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

    @Override
    public String toString() {
        return "ContractStage{" +
                "name='" + name + '\'' +
                ", plannedStartDate=" + plannedStartDate +
                ", actualStartDate=" + actualStartDate +
                ", plannedEndDate=" + plannedEndDate +
                ", actualEndDate=" + actualEndDate +
                ", amount=" + amount +
                ", plannedMaterialCost=" + plannedMaterialCost +
                ", actualMaterialCost=" + actualMaterialCost +
                ", plannedSalaryCost=" + plannedSalaryCost +
                ", actualSalaryCost=" + actualSalaryCost +
                '}';
    }
}
