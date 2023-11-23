package ru.kotb.accounting_system.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The class that describes available types of the contract.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract_types")
public class ContractType extends AbstractEntity {

    @Column(name = "name", unique=true)
    private String name;
}
