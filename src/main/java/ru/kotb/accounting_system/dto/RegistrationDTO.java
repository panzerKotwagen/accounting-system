package ru.kotb.accounting_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * The DTO class that describes user input on register page.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO: don't write full name of the filed in the exception message
public class RegistrationDTO {

    /**
     * The user full name.
     */
    @Length(min = 3, message = "The full name length must be greater than 3")
    @Length(max = 50, message = "The full name length must be less than 50")
    // Each word must be capitalized
    @Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
            message = "Please provide the correct full name")
    public String fullName;

    /**
     * The user unique login.
     */
    @Length(min = 3, message = "The login length must be greater than 3")
    @Length(max = 20, message = "The login length must be less than 20")
    /*
     * The login must consist only of letters and digits.
     */
    @Pattern(regexp = "^[A-Za-z0-9]*$",
            message = "Please provide login consisting only of letters and numbers")
    private String username;

    /**
     * The password which is required to log in.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(min = 8, message = "The password must contain at least 8 characters")
    @Length(max = 20, message = "The password must contain less than 21 characters")
    /*
     * The password must contain at least one uppercase, one lowercase
     * letter, one digit and consist of at least 8 characters.
     */
    @Pattern(regexp = "^(?=.*[A-Z]).+$",
            message = "Please write at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).+$",
            message = "Please write at lest one digit")
    @Pattern(regexp = "^(?=.*[a-z]).+$",
            message = "Please write at lest one lowercase letter")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
