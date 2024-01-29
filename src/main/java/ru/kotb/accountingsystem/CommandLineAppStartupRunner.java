package ru.kotb.accountingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.dao.RoleDAO;
import ru.kotb.accountingsystem.dao.UserDAO;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Component
@Transactional
public class CommandLineAppStartupRunner implements CommandLineRunner {

    UserDAO userDAO;

    RoleDAO roleDAO;

    PasswordEncoder passwordEncoder;

    @Autowired
    public CommandLineAppStartupRunner(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates the possible roles of the users.
     */
    @Override
    public void run(String... args) {
        if (!roleDAO.findByAuthority("USER").isPresent()) {
            roleDAO.save(new Role(Role.Authority.USER));
        }

        if (roleDAO.findByAuthority("ADMIN").isPresent()) {
            return;
        }

        Role adminRole = roleDAO.save(new Role(Role.Authority.ADMIN));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        User admin = new User("Viktor", "admin", passwordEncoder.encode("password"), roles);

        userDAO.save(admin);
    }
}