package ru.kotb.accounting_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * The class that describes the contract that is concluded between
 * the company and the customer.
 */
@Entity
@Table(name = "contracts")
public class Contract {

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
     * The contract amount.
     */
    @Column(name = "amount")
    private int amount;

    /**
     * The type of the contract.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    /**
     * Expected date when does the contract enter into force.
     */
    @Column(name = "planned_start_date")
    private String plannedStartDate;

    /**
     * Actual date when does the contract enter into force.
     */
    @Column(name = "actual_start_date")
    private String actualStartDate;

    /**
     * Expected date when does the contract end.
     */
    @Column(name = "planned_end_date")
    private String plannedEndDate;

    /**
     * Actual date when does the contract end.
     */
    @Column(name = "actual_end_date")
    private String actualEndDate;

    /**
     * The stages of the contract.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private List<ContractStage> contractStages;

    /**
     * The contract with the counterparty that included into the contract.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private List<OrganisationContract> organisationContracts;

    public Contract() {
    }
}
