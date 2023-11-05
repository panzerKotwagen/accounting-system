package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * The entity class that describes the customer, which is linked
 * with the MySQL table "users".
 */
@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {

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
    @Pattern(regexp = "^(?=.*[A-Z]).+$",
            message = "Please write at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).+$",
            message = "Please write at lest one digit")
    @Pattern(regexp = "^(?=.*[a-z]).+$",
            message = "Please write at lest one lowercase letter")
    @Column(name = "password", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The date when the user account stops working.
     */
    @Column(name = "end_date")
    private String endDate;

    /**
     * The roles of the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName="id")}
    )
    private Set<Role> authorities;

    /**
     * No args constructor.
     */
    public User() {
        super();
        authorities = new HashSet<>();
    }

    public User(String fullName, String login, String password, Set<Role> authorities) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Returns the roles of the user.
     *
     * @return the roles of the user
     */
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Return the user login.
     *
     * @return the user login
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return login;
    }

    //TODO: add comments
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
