package ru.kotb.accounting_system.model_assembler;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.OrganisationController;
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
