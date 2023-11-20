package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.exception_handling.DuplicateUsernameException;
import ru.kotb.accounting_system.service.CommonService;


/**
 * The implementation of the UserService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserService extends AbstractService<User, UserDAO>
        implements CommonService<User>, UserDetailsService {

    /**
     * The password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        super(userDAO);
        userDAO.setClass(User.class);
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

        return DAO.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User is not valid"));
    }

    /**
     * Adds new entity to the table.
     *
     * @param entity new entity
     */
    @Override
    public User saveOrUpdate(User entity) {
        //TODO: password check when update
        User user = DAO.findByUsername(entity.getUsername())
                .orElse(null);

        if (user != null && entity.getId() != user.getId())
            throw new DuplicateUsernameException("This username is already taken");

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.saveOrUpdate(entity);
    }
}
