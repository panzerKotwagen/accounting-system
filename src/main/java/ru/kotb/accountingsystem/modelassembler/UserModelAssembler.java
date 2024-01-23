package ru.kotb.accountingsystem.modelassembler;

import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.controller.UserController;
import ru.kotb.accountingsystem.entity.User;


/**
 * The model assembler for the {@code User} entity.
 */
@Component
public class UserModelAssembler
        extends AbstractModelAssembler<User, UserController> {

    public UserModelAssembler() {
        super(UserController.class, "users");
    }
}
