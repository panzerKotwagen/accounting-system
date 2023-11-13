package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.ICommonDAO;
import ru.kotb.accounting_system.entity.Organisation;


/**
 * The implementation of the OrganisationService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationService
        extends AbstractService<Organisation, ICommonDAO<Organisation>> {

    @Autowired
    public OrganisationService(ICommonDAO<Organisation> organisationDAO) {
        super(organisationDAO);
        organisationDAO.setClass(Organisation.class);
    }
}
