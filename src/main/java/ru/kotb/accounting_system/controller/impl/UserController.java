package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.controller.CommonController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.UserService;


/**
 * The controller class that processes requests to /api/users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController<User, UserService>
        implements CommonController<User> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param service the user service bean
     */
    @Autowired
    public UserController(
            UserService service, CommonModelAssembler<User> assembler) {

        super(service, assembler);
    }
}
