package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.repository.CommonRepository;
import ru.kotb.accountingsystem.entity.Organisation;


/**
 * The service for working with {@code Organisation} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationService
        extends AbstractService<Organisation, CommonRepository<Organisation>> {

    @Autowired
    public OrganisationService(CommonRepository<Organisation> organisationDAO) {
        super(organisationDAO);
    }
}
