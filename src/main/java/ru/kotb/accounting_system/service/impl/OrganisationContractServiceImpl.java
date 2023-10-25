package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kotb.accounting_system.dao.GenericDAO;
import ru.kotb.accounting_system.entity.OrganisationContract;
import ru.kotb.accounting_system.service.OrganisationContractService;


/**
 * The implementation of the OrganisationContractService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class OrganisationContractServiceImpl extends AbstractEntityService<OrganisationContract>
implements OrganisationContractService {

    @Autowired
    public OrganisationContractServiceImpl(GenericDAO<OrganisationContract> genericDAOImpl) {
        super(genericDAOImpl);
        genericDAOImpl.setClass(OrganisationContract.class);
    }
}
