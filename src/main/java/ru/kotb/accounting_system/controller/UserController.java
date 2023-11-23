package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.model_assembler.CommonModelAssembler;
import ru.kotb.accounting_system.service.CommonService;
import ru.kotb.accounting_system.service.impl.UserService;


/**
 * The controller class that processes requests to /api/users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController<User, CommonService<User>> {

    @Autowired
    public UserController(
            UserService service, CommonModelAssembler<User> assembler) {

        super(service, assembler);
    }
}
