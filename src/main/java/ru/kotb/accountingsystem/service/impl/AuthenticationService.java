package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.repository.RoleRepository;
import ru.kotb.accountingsystem.repository.UserDAO;
import ru.kotb.accountingsystem.dto.RegistrationDTO;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.exception.handling.DuplicateUsernameException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


/**
 * The service that provides registration and authentication of the
 * users.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
@Validated
public class AuthenticationService {

    /**
     * The DAO object for getting access to the "users" table.
     */
    private final UserDAO userDAO;

    /**
     * The DAO object for getting access to the "roles" table.
     */
    private final RoleRepository roleDAO;

    /**
     * The password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the bean and inject the dependencies.
     *
     * @param userDAO         the user DAO
     * @param roleDAO         the role DAO
     * @param passwordEncoder the password encoder
     */
    @Autowired
    public AuthenticationService(UserDAO userDAO, RoleRepository roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Accepts the {@code RegistrationDTO} as input, and saves it in
     * the database with the user role.
     *
     * @param registrationDTO the {@code RegistrationDTO} with full
     *                        name, username and password
     * @return the saved user
     */
    @Transactional
    public User registerUser(@Valid RegistrationDTO registrationDTO) {
        if (userDAO.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("This username is already taken");
        }

        Role userRole = roleDAO.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());

        User newUser = new User(
                registrationDTO.getFullName(),
                registrationDTO.getUsername(),
                encodedPassword,
                authorities);
        return userDAO.save(newUser);
    }
}
