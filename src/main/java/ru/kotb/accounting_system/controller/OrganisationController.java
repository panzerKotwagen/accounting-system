package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.entity.Organisation;


/**
 * The controller class that processes requests to /api/organisations.
 */
@RequestMapping("/api/organisations")
public interface OrganisationController extends CommonController<Organisation> {
}
