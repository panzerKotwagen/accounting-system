package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.UserController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.model_assembler.impl.UserModelAssemblerImpl;
import ru.kotb.accounting_system.service.UserService;


/**
 * The controller class that processes requests to /api/users.
 */
@RestController
public class UserControllerImpl extends AbstractController<User, UserService>
        implements UserController {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the user service bean
     */
    @Autowired
    public UserControllerImpl(UserService service, UserModelAssemblerImpl assembler) {
        super(service, assembler);
    }
}
