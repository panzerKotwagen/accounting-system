package ru.kotb.accounting_system.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
    @Length(min=3, max=150)
    @Column(name = "full_name")
    private String fullName;

    /**
     * The user login.
     */
    @NotBlank
    @Length(min=3, max=20)
    @Column(name = "login", unique = true)
    private String login;

    /**
     * The password which is required to log in.
     */
    @NotBlank
    @Length(min=8, max=20)
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
