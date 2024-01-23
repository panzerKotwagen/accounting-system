package ru.kotb.accountingsystem.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kotb.accountingsystem.dao.UserDAO;
import ru.kotb.accountingsystem.entity.User;
import ru.kotb.accountingsystem.exception.handling.DuplicateUsernameException;
import ru.kotb.accountingsystem.service.impl.UserService;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final PasswordEncoder localPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsername() {
        // given
        String username = "username";
        given(userDAO.findByUsername(username)).willReturn(Optional.of(new User()));

        // when
        userService.loadUserByUsername(username);

        // then
        verify(userDAO).findByUsername(username);
    }

    @Test
    void loadUserByNonExistingUsernameThrowsUsernameNotFoundException() {
        // given
        String username = "username";

        // then
        Assertions.assertThatThrownBy(() -> userService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void saveUser() {
        // given
        User user = new User(
                "Viktor",
                "viktor",
                "12345678Af",
                new HashSet<>());

        given(passwordEncoder.encode(Mockito.anyString()))
                .willReturn(localPasswordEncoder.encode(user.getPassword()));
        user.setPassword(localPasswordEncoder.encode(user.getPassword()));
        given(userDAO.save(user)).willReturn(user);

        // when
        User savedUser = userService.save(user);

        // then
        Assertions.assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void updateUserWithExistingUsernameButWithIncorrectIdThrowsDuplicateUsernameException() {
        // given
        User savedUser = new User(
                "Viktor",
                "viktor",
                "12345678Af",
                new HashSet<>());
        savedUser.setId(2);

        User existingUser = new User(
                "Viktor",
                "viktor",
                "12345678Af",
                new HashSet<>());
        existingUser.setId(1);

        given(userDAO.findByUsername(savedUser.getUsername()))
                .willReturn(Optional.of(existingUser));

        // then
        Assertions.assertThatThrownBy(() -> userService.update(savedUser))
                .isInstanceOf(DuplicateUsernameException.class);

        verify(userDAO, never()).save(any());
    }
}