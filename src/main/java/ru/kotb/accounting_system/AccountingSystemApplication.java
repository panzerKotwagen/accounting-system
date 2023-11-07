package ru.kotb.accounting_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.entity.Role;
import ru.kotb.accounting_system.entity.User;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AccountingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncode) {
        return args -> {
            if (roleDAO.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = new Role("ADMIN");
            roleDAO.saveOrUpdate(adminRole);
            roleDAO.saveOrUpdate(new Role("USER"));
            System.out.println("Create Admin");

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User("Viktor Yablokov", "admin", passwordEncode.encode("password"), roles);

            userDAO.saveOrUpdate(admin);
        };
    }
}
