package ru.kotb.accounting_system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.ContractStageDAO;
import ru.kotb.accounting_system.entity.ContractStage;

import javax.persistence.EntityManager;


/**
 * The implementation of the ContractStageDAO interface.
 */
@Repository
public class ContractStageDAOImpl extends AbstractDAO<ContractStage>
        implements ContractStageDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the EntityManager object
     */
    @Autowired
    public ContractStageDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }
}
