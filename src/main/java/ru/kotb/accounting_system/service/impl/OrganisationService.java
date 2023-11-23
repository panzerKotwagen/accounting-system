package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.entity.Organisation;


/**
 * The service for working with {@code Organisation} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationService
        extends AbstractService<Organisation, CommonDAO<Organisation>> {

    @Autowired
    public OrganisationService(CommonDAO<Organisation> organisationDAO) {
        super(organisationDAO);
        organisationDAO.setClass(Organisation.class);
    }
}
