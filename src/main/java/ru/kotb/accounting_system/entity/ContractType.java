package ru.kotb.accounting_system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The class that describes available types of the contract.
 */
@Entity
@Table(name = "contract_types")
public class ContractType extends AbstractEntity {

    @Column(name = "name", unique=true)
    private String name;

    public ContractType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
