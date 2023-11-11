package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.IUserDAO;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.CommonService;


/**
 * The implementation of the UserService interface.
 */
@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserService extends AbstractService<User, IUserDAO>
        implements CommonService<User>, UserDetailsService {

    @Autowired
    public UserService(IUserDAO userDAO) {
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
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return entityDAO.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("user is not valid"));
    }
}
