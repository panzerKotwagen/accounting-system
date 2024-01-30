package ru.kotb.accountingsystem.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kotb.accountingsystem.repository.RoleRepository;
import ru.kotb.accountingsystem.dto.RegistrationDTO;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.exception.handling.DuplicateUsernameException;
import ru.kotb.accountingsystem.repository.UserRepository;
import ru.kotb.accountingsystem.service.impl.AuthenticationService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRep;

    @Mock
    private RoleRepository roleRep;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService service;

    @Test
    public void registerUserReturnNewUser() {
        Role role = new Role(Role.Authority.USER);
        RegistrationDTO dto = new RegistrationDTO(
                "Name", "Username", "123456Aa");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);

        User newUser = new User(
                dto.getFullName(),
                dto.getUsername(),
                dto.getPassword(),
                authorities);

        when(userRep.save(Mockito.any(User.class))).thenReturn(newUser);
        when(roleRep.findByAuthority(Role.Authority.valueOf("USER"))).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn(dto.getPassword());

        User savedUser = service.registerUser(dto);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void registerUserWithExistingUsernameThrowsException() {
        // given
        given(userRep.findByUsername(any()))
                .willReturn(Optional.of(new User()));

        // then
        Assertions.assertThatThrownBy(
                () -> service.registerUser(new RegistrationDTO()))
                .isInstanceOf(DuplicateUsernameException.class);
    }
}
