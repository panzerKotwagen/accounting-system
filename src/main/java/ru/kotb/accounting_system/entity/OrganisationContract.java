package ru.kotb.accounting_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The class that describes a contract with a counterparty company.
 */
@Entity
@Table(name = "organisation_contracts")
public class OrganisationContract extends AbstractEntity {

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
    @JoinColumn(name = "organisation_id")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(String plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public String getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(String plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
}
