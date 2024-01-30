package ru.kotb.accountingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.exception.handling.DuplicateUsernameException;
import ru.kotb.accountingsystem.repository.UserRepository;
import ru.kotb.accountingsystem.service.CommonService;

import javax.validation.Valid;


/**
 * The service for working with {@code User} entity.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
@Validated
public class UserService extends AbstractService<User, UserRepository>
        implements CommonService<User>, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRep, PasswordEncoder passwordEncoder) {
        super(userRep);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Returns the {@code UserDetails} object which is need to
     * authentication by username.
     *
     * @param username the login of the user
     * @return the {@code UserDetails} object
     * @throws UsernameNotFoundException user with this username was
     *                                   not found
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User is not valid"));
    }

    /**
     * Adds new entity to the table.
     *
     * @param entity new entity
     */
    @Override
    public User save(@Valid User entity) {
        if (repository.findByUsername(entity.getUsername()).isPresent())
            throw new DuplicateUsernameException("This username is already taken");

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

    /**
     * Updates the specified entity. When the entity with given id
     * was not found then throw {@code NoSuchEntityException}.
     */
    @Override
    public User update(@Valid User entity) {
        User userWithSameUsername = repository.findByUsername(entity.getUsername())
                .orElse(null);

        if (userWithSameUsername != null
                && entity.getId() != userWithSameUsername.getId()) {

            throw new DuplicateUsernameException("This username is already taken");
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.update(entity);
    }
}
