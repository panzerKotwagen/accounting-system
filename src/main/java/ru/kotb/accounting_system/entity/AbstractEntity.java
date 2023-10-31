package ru.kotb.accounting_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The abstract entity with common attribute "ID".
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * The entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
