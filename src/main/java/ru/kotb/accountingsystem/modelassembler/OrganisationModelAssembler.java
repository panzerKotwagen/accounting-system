package ru.kotb.accountingsystem.modelassembler;

import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.controller.OrganisationController;
import ru.kotb.accountingsystem.entity.Organisation;


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
