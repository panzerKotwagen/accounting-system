package ru.kotb.accounting_system.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The abstract entity with common attribute "ID".
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * The entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
