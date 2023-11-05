package ru.kotb.accounting_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.Role;
import ru.kotb.accounting_system.entity.User;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class AuthenticationService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleDAO.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        User newUser = new User(username, encodedPassword, authorities);
        userDAO.saveOrUpdate(newUser);

        return newUser;
    }
}
