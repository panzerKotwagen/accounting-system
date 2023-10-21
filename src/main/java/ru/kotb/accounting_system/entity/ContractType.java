package ru.kotb.accounting_system.entity;


import javax.persistence.*;

/**
 * The Class that describes available types of the contract.
 */
@Entity
@Table(name = "contract_types")
public class ContractType {

    /**
     * The entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique=true)
    private String name;

    public ContractType() {
    }
}
