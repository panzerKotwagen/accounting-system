package ru.kotb.accounting_system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The class that describes the contracting company.
 */
@Entity
@Table(name = "organisations")
public class Organisation {

    /**
     * The entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public Organisation(String name, String address, String TIN) {
        this.name = name;
        this.address = address;
        this.TIN = TIN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", TIN='" + TIN + '\'' +
                '}';
    }
}
