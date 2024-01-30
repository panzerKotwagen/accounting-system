package ru.kotb.accountingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.repository.RoleRepository;
import ru.kotb.accountingsystem.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Component
@Transactional
public class CommandLineAppStartupRunner implements CommandLineRunner {

    UserRepository userRep;

    RoleRepository roleRep;

    PasswordEncoder passwordEncoder;

    @Autowired
    public CommandLineAppStartupRunner(UserRepository userRep, RoleRepository roleRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.roleRep = roleRep;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates the possible roles of the users.
     */
    @Override
    public void run(String... args) {
        if (!roleRep.findByAuthority(Role.Authority.valueOf("USER")).isPresent()) {
            roleRep.save(new Role(Role.Authority.USER));
        }

        if (roleRep.findByAuthority(Role.Authority.valueOf("ADMIN")).isPresent()) {
            return;
        }

        Role adminRole = roleRep.save(new Role(Role.Authority.ADMIN));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        User admin = new User("Viktor", "admin", passwordEncoder.encode("password"), roles);

        userRep.save(admin);
    }
}