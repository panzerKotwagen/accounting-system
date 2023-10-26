package ru.kotb.accounting_system.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kotb.accounting_system.dao.OrganisationDAO;
import ru.kotb.accounting_system.entity.Organisation;


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
     * @param sessionFactory the SessionFactory object
     */
    @Autowired
    public OrganisationDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
