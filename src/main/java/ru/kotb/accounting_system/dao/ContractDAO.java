package ru.kotb.accounting_system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.entity.Contract;

import javax.persistence.EntityManager;


/**
 * The DAO to work with the {@code Controller} entity.
 */
@Repository
public class ContractDAO extends AbstractDAO<Contract> {

    @Autowired
    public ContractDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
