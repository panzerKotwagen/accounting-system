package ru.kotb.accounting_system.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kotb.accounting_system.entity.User;


/**
 * The interface that provides working with the users.
 */
public interface UserService extends CommonService<User>,
        UserDetailsService {
}
