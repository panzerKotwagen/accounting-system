package ru.kotb.accounting_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Contract extends AbstractEntity {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
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

    public List<ContractStage> getContractStages() {
        return contractStages;
    }

    public void setContractStages(List<ContractStage> contractStages) {
        this.contractStages = contractStages;
    }

    public List<OrganisationContract> getOrganisationContracts() {
        return organisationContracts;
    }

    public void setOrganisationContracts(List<OrganisationContract> organisationContracts) {
        this.organisationContracts = organisationContracts;
    }
}
