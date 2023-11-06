package ru.kotb.accounting_system.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.OrganisationDAO;
import ru.kotb.accounting_system.entity.Organisation;

import javax.persistence.EntityManager;


/**
 * The implementation of the OrganisationDAO interface.
 */
@Repository
public class OrganisationDAOImpl extends AbstractDAO<Organisation>
        implements OrganisationDAO {

    /**
     * Creates the component and binds it with the sessionFactory
     * object.
     *
     * @param sessionFactory the EntityManager object
     */
    @Autowired
    public OrganisationDAOImpl(EntityManager sessionFactory) {
        super(sessionFactory);
    }
}
