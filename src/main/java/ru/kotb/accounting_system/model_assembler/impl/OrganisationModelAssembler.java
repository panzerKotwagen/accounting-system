package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.impl.OrganisationController;
import ru.kotb.accounting_system.entity.Organisation;


/**
 * The model assembler for the {@code Organisation} entity.
 */
@Component
public class OrganisationModelAssembler
        extends AbstractModelAssembler<Organisation, OrganisationController> {

    public OrganisationModelAssembler() {
        super(OrganisationController.class, "organisations");
    }
}
