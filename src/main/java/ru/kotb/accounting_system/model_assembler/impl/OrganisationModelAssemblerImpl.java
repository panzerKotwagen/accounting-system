package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.OrganisationController;
import ru.kotb.accounting_system.entity.Organisation;
import ru.kotb.accounting_system.model_assembler.OrganisationModelAssembler;


/**
 * The model assembler for the {@code Organisation} entity.
 */
@Component
public class OrganisationModelAssemblerImpl
        extends AbstractModelAssembler<Organisation, OrganisationController>
        implements OrganisationModelAssembler {


    public OrganisationModelAssemblerImpl() {
        super(OrganisationController.class, "organisations");
    }
}
