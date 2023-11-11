package ru.kotb.accounting_system.model_assembler.impl;

import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.controller.UserController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.model_assembler.UserModelAssembler;

/**
 * The model assembler for the {@code User} entity.
 */
@Component
public class UserModelAssemblerImpl
        extends AbstractModelAssembler<User, UserController>
        implements UserModelAssembler {


    public UserModelAssemblerImpl() {
        super(UserController.class, "users");
    }
}
