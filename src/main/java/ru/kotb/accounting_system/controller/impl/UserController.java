package ru.kotb.accounting_system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.exception_handling.NoSuchEntityException;
import ru.kotb.accounting_system.service.UserService;

import java.util.List;


/**
 * The Controller class that processes requests to /api/users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController<User, UserService> {

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param userService the user service bean
     */
    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }
}
