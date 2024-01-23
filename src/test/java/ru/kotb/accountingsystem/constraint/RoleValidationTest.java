package ru.kotb.accountingsystem.constraint;

import org.junit.jupiter.api.Test;
import ru.kotb.accountingsystem.entity.Role;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class RoleValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void authorityNameMustBeValid1() {
        Role role = new Role("USeR");

        Set<ConstraintViolation<Role>> violations = validator.validate(role);

        ConstraintViolation<Role> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(role.getAuthority());
    }

    @Test
    void authorityNameMustBeValid2() {
        Role role = new Role("USER1");

        Set<ConstraintViolation<Role>> violations = validator.validate(role);

        ConstraintViolation<Role> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(role.getAuthority());
    }

    @Test
    void authorityNameMustBeValid3() {
        Role role = new Role("US ER");

        Set<ConstraintViolation<Role>> violations = validator.validate(role);

        ConstraintViolation<Role> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(role.getAuthority());
    }
}
