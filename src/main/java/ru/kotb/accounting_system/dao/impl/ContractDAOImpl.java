package ru.kotb.accounting_system.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.ContractDAO;
import ru.kotb.accounting_system.entity.Contract;


/**
 * The implementation of the ContractAO interface.
 */
@Repository
public class ContractDAOImpl extends AbstractDAO<Contract> implements ContractDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public ContractDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
