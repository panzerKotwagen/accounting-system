package ru.kotb.accounting_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.Role;
import ru.kotb.accounting_system.entity.User;

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

    @Override
    public void run(String... args) {
        if (!roleDAO.findByAuthority("USER").isPresent()) {
            roleDAO.save(new Role("USER"));
        }

        if (roleDAO.findByAuthority("ADMIN").isPresent()) return;
        Role adminRole = roleDAO.save(new Role("ADMIN"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        User admin = new User("Viktor", "admin", passwordEncoder.encode("password"), roles);

        userDAO.save(admin);
    }
}