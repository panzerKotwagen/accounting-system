package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accountingsystem.entity.Organisation;
import ru.kotb.accountingsystem.repository.OrganisationRepository;


/**
 * The service for working with {@code Organisation} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationService
        extends AbstractService<Organisation, OrganisationRepository> {

    @Autowired
    public OrganisationService(OrganisationRepository organisationRep) {
        super(organisationRep);
    }
}
