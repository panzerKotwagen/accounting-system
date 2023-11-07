package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;
import java.util.List;


/**
 * The class that describes the contract that is concluded between
 * the company and the customer.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract extends AbstractEntity {

    /**
     * The contract name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=100, message = "The maximum field length is limited to 100 characters")
    @Column(name = "name")
    private String name;

    /**
     * The contract amount.
     */
    @NotNull(message = "The field cannot be empty")
    @PositiveOrZero(message = "The value cannot be below zero")
    @Column(name = "amount")
    private int amount;

    /**
     * The type of the contract.
     */
    @NotNull(message = "The field cannot be empty")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    /**
     * Expected date when does the contract enter into force.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_start_date")
    @FutureOrPresent
    private Date plannedStartDate;

    /**
     * Actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    @FutureOrPresent
    private Date actualStartDate;

    /**
     * Expected date when does the contract end.
     */
    @NotNull(message = "The field cannot be empty")
    @Column(name = "planned_end_date")
    @FutureOrPresent
    private Date plannedEndDate;

    /**
     * Actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    @FutureOrPresent
    private Date actualEndDate;

    /**
     * The stages of the contract.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ContractStage> contractStages;

    /**
     * The contract with the counterparty that included into the contract.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CounterpartyContract> counterpartyContracts;
}
