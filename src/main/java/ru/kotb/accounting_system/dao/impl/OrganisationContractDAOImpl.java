package ru.kotb.accounting_system.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.OrganisationContractDAO;
import ru.kotb.accounting_system.entity.OrganisationContract;

import javax.persistence.EntityManager;


/**
 * The implementation of the OrganisationContractDAO interface.
 */
@Repository
public class OrganisationContractDAOImpl extends AbstractDAO<OrganisationContract>
        implements OrganisationContractDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the EntityManager object
     */
    @Autowired
    public OrganisationContractDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }
}
