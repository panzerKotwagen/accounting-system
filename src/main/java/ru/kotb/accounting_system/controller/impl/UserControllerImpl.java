package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;


/**
 * The controller class that processes requests to /api/users.
 */
@RestController
@RequestMapping("/api/users")
public class UserControllerImpl extends AbstractController<User, UserService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param userService the user service bean
     */
    @Autowired
    public UserControllerImpl(UserService userService) {
        super(userService);
    }
}
