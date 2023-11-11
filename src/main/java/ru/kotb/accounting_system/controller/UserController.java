package ru.kotb.accounting_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.kotb.accounting_system.entity.User;


/**
 * The controller class that processes requests to /api/users.
 */
@RequestMapping("/api/users")
public interface UserController extends CommonController<User> {
}
