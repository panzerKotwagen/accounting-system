package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.CommonDAO;
import ru.kotb.accounting_system.dao.OrganisationDAO;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.service.OrganisationService;


/**
 * The implementation of the OrganisationService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationServiceImpl
        extends AbstractService<Organisation, OrganisationDAO>
        implements OrganisationService {

    @Autowired
    public OrganisationServiceImpl(OrganisationDAO organisationDAO) {
        super(organisationDAO);
        organisationDAO.setClass(Organisation.class);
    }
}
