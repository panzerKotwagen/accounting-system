package ru.kotb.accountingsystem.constraint;

import org.junit.jupiter.api.Test;
import ru.kotb.accountingsystem.entity.Role;
import ru.kotb.accountingsystem.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class UserValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final Set<Role> authorities = Collections.singleton(new Role("USER"));

    @Test
    void fullNameMustNotBeNull() {
        User user = new User(null, "Username", "1234567Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getFullName());
    }

    @Test
    void usernameMustNotBeNull() {
        User user = new User("Name", null, "1234567Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getUsername());
    }

    @Test
    void passwordMustContain1LowercaseLetter() {
        User user = new User("Name", "Username", "1234567A", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getPassword());
    }

    @Test
    void passwordMustContain1UppercaseLetter() {
        User user = new User("Name", "Username", "1234567a", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getPassword());
    }

    @Test
    void passwordMustContain8Chars() {
        User user = new User("Name", "Username", "12345Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getPassword());
    }

    @Test
    void fullNameMustBeValid1() {
        User user = new User("Name123", "Username", "123456Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getFullName());
    }

    @Test
    void fullNameMustBeValid2() {
        User user = new User("Name name", "Username", "123456Aa", authorities);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        ConstraintViolation<User> v = violations.iterator().next();
        assertThat(v.getInvalidValue()).isEqualTo(user.getFullName());
    }

    @Test
    void fullNameMustBeValid3() {
        User user = new User("name", "Username", "123456Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getFullName());
    }

    @Test
    void fullNameMustBeValid4() {
        User user = new User("Name!", "Username", "123456Aa", authorities);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        ConstraintViolation<User> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(user.getFullName());
    }

    @Test
    void authoritiesMustNotBeEmpty() {
        User user = new User("Name", "Username", "123456Aa", new HashSet<>());

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).isNotEmpty();
    }
}
