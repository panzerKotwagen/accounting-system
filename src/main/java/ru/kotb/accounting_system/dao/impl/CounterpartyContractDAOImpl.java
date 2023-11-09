package ru.kotb.accounting_system.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.CounterpartyContractDAO;
import ru.kotb.accounting_system.entity.CounterpartyContract;

import javax.persistence.EntityManager;


/**
 * The implementation of the OrganisationContractDAO interface.
 */
@Repository
public class CounterpartyContractDAOImpl extends AbstractDAO<CounterpartyContract>
        implements CounterpartyContractDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the EntityManager object
     */
    @Autowired
    public CounterpartyContractDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }
}
