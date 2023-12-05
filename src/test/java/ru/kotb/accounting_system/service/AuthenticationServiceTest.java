package ru.kotb.accounting_system.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kotb.accounting_system.dao.RoleDAO;
import ru.kotb.accounting_system.dao.UserDAO;
import ru.kotb.accounting_system.dto.RegistrationDTO;
import ru.kotb.accounting_system.entity.Role;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.impl.AuthenticationService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService service;

    @Test
    public void registerUserReturnNewUser() {
        Role role = new Role("USER");
        RegistrationDTO dto = new RegistrationDTO(
                "Name", "Username", "123456Aa");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);

        User newUser = new User(
                dto.getFullName(),
                dto.getUsername(),
                dto.getPassword(),
                authorities);

        when(userDAO.save(Mockito.any(User.class))).thenReturn(newUser);
        when(roleDAO.findByAuthority("USER")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn(dto.getPassword());

        User savedUser = service.registerUser(dto);

        Assertions.assertThat(savedUser).isNotNull();
    }
}
