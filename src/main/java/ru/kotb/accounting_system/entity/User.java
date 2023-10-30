package ru.kotb.accounting_system.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * The Entity class that describes the customer, which is linked
 * with the MySQL table "users".
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    /**
     * The user full name.
     */
    @NotBlank
    @Length(min=3, max=50)
    // Each word must be capitalized
    @Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$")
    @Column(name = "full_name")
    private String fullName;

    /**
     * The user login.
     */
    @NotBlank
    @Length(min=3, max=20)
    /*
     * The login must consist only of letters and digits, digits must
     * be after letters.
     */
    @Pattern(regexp = "^[A-Za-z]+(?:[A-Za-z0-9]+)*$",
            message = "The login must consist only of letters and digits, " +
                    "digits must be after letters.")
    @Column(name = "login", unique = true)
    private String login;

    /**
     * The password which is required to log in.
     */
    @NotBlank
    @Length(min=8, max=20)
    /*
     * The password must contain at least one uppercase, one lowercase
     * letter, one digit and consist of at least 8 characters.
     */
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$")
    @Column(name = "password", unique = true)
    private String password;

    /**
     * The date when the user account stops working.
     */
    @NotNull
    @Column(name = "end_date")
    private String endDate;

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
