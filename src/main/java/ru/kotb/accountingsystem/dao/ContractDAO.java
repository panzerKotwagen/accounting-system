package ru.kotb.accountingsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accountingsystem.entity.Contract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;


/**
 * The DAO to work with the {@code Controller} entity.
 */
@Repository
public class ContractDAO extends AbstractDAO<Contract> {

    @Autowired
    public ContractDAO(EntityManager entityManager) {
        super(entityManager);
        setClass(Contract.class);
    }

    public List<Contract> findAllWhereDateBetween(Date start, Date end) {
        Query query = entityManager.createQuery(" FROM Contract " +
                "WHERE plannedStartDate >= :start" +
                " and plannedEndDate <= :end", Contract.class);

        query.setParameter("start", start);
        query.setParameter("end", end);

        return query.getResultList();
    }
}
