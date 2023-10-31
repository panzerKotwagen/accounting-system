package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * The Entity class that describes the customer, which is linked
 * with the MySQL table "users".
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    /**
     * The user full name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(min = 3, message = "The full name length must be greater than 3")
    @Length(max = 50, message = "The full name length must be less than 50")
    // Each word must be capitalized
    @Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
            message = "Please provide the correct full name")
    @Column(name = "full_name")
    private String fullName;

    /**
     * The user login.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(min = 3, message = "The login length must be greater than 3")
    @Length(max = 20, message = "The login length must be less than 3")
    /*
     * The login must consist only of letters and digits.
     */
    @Pattern(regexp = "^[A-Za-z0-9]*$",
            message = "The login must consist only of letters and digits")
    @Column(name = "login", unique = true)
    private String login;

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
    @Pattern(regexp = "^(?=.*[A-Z]).{1,}$",
            message = "Please write at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).{1,}$",
            message = "Please write at lest one digit")
    @Pattern(regexp = "^(?=.*[a-z]).{1,}$",
            message = "Please write at lest one lowercase letter")
    @Column(name = "password", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The date when the user account stops working.
     */
    @Column(name = "end_date")
    private String endDate;
}
