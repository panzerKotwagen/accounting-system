package ru.kotb.accounting_system.service;

import ru.kotb.accounting_system.dto.RegistrationDTO;
import ru.kotb.accounting_system.entity.User;

import javax.validation.Valid;


/**
 * The interface that provides registration and authentication of the
 * users.
 */
public interface AuthenticationService {

    /**
     * Accepts the {@code RegistrationDTO} as input, and saves it in
     * the database with the user role.
     *
     * @param registrationDTO the {@code RegistrationDTO} with full
     *                        name, username and password
     * @return the saved user
     */
    User registerUser(@Valid RegistrationDTO registrationDTO);
}
