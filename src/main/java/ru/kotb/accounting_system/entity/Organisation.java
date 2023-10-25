package ru.kotb.accounting_system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The class that describes the contracting company.
 */
@Entity
@Table(name = "organisations")
public class Organisation extends AbstractEntity {

    /**
     * The counterparty company name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The company address.
     */
    @Column(name = "address")
    private String address;

    /**
     * The unique code that regulates the accounting of taxpayers.
     */
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
