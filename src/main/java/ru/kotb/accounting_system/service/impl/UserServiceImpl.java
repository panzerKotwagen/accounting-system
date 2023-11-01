package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;


/**
 * The implementation of the UserService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl extends AbstractService<User, UserDAO>
        implements UserService {

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        userDAO.setClass(User.class);
    }

    /**
     * Returns the {@code UserDetails} object which is need to
     * authentication by username.
     *
     * @param username the login of the user
     * @return the {@code UserDetails} object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = entityDAO.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
