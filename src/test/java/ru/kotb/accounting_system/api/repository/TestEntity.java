package ru.kotb.accounting_system.api.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.kotb.accounting_system.entity.AbstractEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestEntity extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
