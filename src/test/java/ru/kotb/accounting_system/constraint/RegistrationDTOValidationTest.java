package ru.kotb.accounting_system.constraint;

import org.junit.jupiter.api.Test;
import ru.kotb.accounting_system.dto.RegistrationDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationDTOValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void passwordMustContain1LowercaseLetter() {
        RegistrationDTO regDTO = new RegistrationDTO(null, "Username", "1234567A");

        Set<ConstraintViolation<RegistrationDTO>> violations = validator.validate(regDTO);

        ConstraintViolation<RegistrationDTO> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(regDTO.getPassword());
    }

    @Test
    void passwordMustContain1UppercaseLetter() {
        RegistrationDTO regDTO = new RegistrationDTO(null, "Username", "1234567a");

        Set<ConstraintViolation<RegistrationDTO>> violations = validator.validate(regDTO);

        ConstraintViolation<RegistrationDTO> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(regDTO.getPassword());
    }

    @Test
    void passwordMustContain8Chars() {
        RegistrationDTO regDTO = new RegistrationDTO(null, "Username", "12345Aa");

        Set<ConstraintViolation<RegistrationDTO>> violations = validator.validate(regDTO);

        ConstraintViolation<RegistrationDTO> v = violations.iterator().next();

        assertThat(v.getInvalidValue()).isEqualTo(regDTO.getPassword());
    }

    @Test
    void fullNameCanBeNull() {
        RegistrationDTO regDTO = new RegistrationDTO(null, "Username", "123456Aa");

        Set<ConstraintViolation<RegistrationDTO>> violations = validator.validate(regDTO);

        assertThat(violations).isEmpty();
    }
}
