package ru.kotb.accounting_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The class that describes a contract with a counterparty company.
 */
@Entity
@Table(name = "organisation_contracts")
public class OrganisationContract {

    /**
     * The entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * The contract name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The type of the contract.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    /**
     * The counterparty with which the contract was concluded.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counterparty_organisation_id")
    private Organisation organisation;

    /**
     * The contract amount.
     */
    @Column(name = "amount")
    private int amount;

    /**
     * Expected date when does the contract enter into force.
     */
    @Column(name = "planned_start_date")
    private String plannedStartDate;

    /**
     * Expected date when does the contract end.
     */
    @Column(name = "planned_end_date")
    private String plannedEndDate;

    /**
     * Actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    private String actualStartDate;

    /**
     * Actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    private String actualEndDate;

    public OrganisationContract() {
    }
}
