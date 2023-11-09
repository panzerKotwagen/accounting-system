package ru.kotb.accounting_system.entity;

import lombok.AllArgsConstructor;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organisation_contracts")
public class CounterpartyContract extends AbstractContract {

    /**
     * The counterparty with which the contract was concluded.
     */
    @NotNull(message = "The field cannot be empty")
    @ManyToOne()
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
