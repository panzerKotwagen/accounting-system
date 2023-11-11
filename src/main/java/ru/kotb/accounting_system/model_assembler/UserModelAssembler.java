package ru.kotb.accounting_system.model_assembler;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.UserController;
import ru.kotb.accounting_system.entity.User;


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
