package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.dto.RegistrationDTO;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.exception.handling.DuplicateUsernameException;
import ru.kotb.accountingsystem.repository.RoleRepository;
import ru.kotb.accountingsystem.repository.UserRepository;

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
     * The repository object for getting access to the "users" table.
     */
    private final UserRepository userRep;

    /**
     * The repository object for getting access to the "roles" table.
     */
    private final RoleRepository roleRep;

    /**
     * The password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the bean and inject the dependencies.
     *
     * @param userRep         the user repository
     * @param roleRep         the role repository
     * @param passwordEncoder the password encoder
     */
    @Autowired
    public AuthenticationService(UserRepository userRep, RoleRepository roleRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.roleRep = roleRep;
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
        if (userRep.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("This username is already taken");
        }

        Role userRole = roleRep.findByAuthority(Role.Authority.valueOf("USER")).get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());

        User newUser = new User(
                registrationDTO.getFullName(),
                registrationDTO.getUsername(),
                encodedPassword,
                authorities);
        return userRep.save(newUser);
    }
}
