package ru.kotb.accounting_system.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * The class that describes the contracting company.
 */
@Entity
@Table(name = "organisations")
public class Organisation extends AbstractEntity {

    /**
     * The counterparty company name.
     */
    @NotBlank
    @Length(min=3, max=150)
    @Column(name = "name")
    private String name;

    /**
     * The company address.
     */
    @NotBlank
    @Length(min=3, max=150)
    @Column(name = "address")
    private String address;

    /**
     * The unique code that regulates the accounting of taxpayers.
     */
    @NotBlank
    @Length(min=10, max=10)
    @Column(name = "TIN")
    private String TIN;

    public Organisation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    @Override
    public String toString() {
        return "Counterparty{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", TIN='" + TIN + '\'' +
                '}';
    }
}
