package ru.kotb.accountingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.modelassembler.CommonModelAssembler;
import ru.kotb.accountingsystem.service.CommonService;
import ru.kotb.accountingsystem.service.impl.UserService;


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
