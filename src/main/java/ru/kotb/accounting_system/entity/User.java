package ru.kotb.accounting_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
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
    @Column(name = "full_name")
    private String fullName;

    /**
     * The user login.
     */
    @NotBlank(message = "The field cannot be empty")
    @Column(name = "login", unique = true)
    private String username;

    /**
     * The password which is required to log in.
     */
    @NotBlank(message = "The field cannot be empty")
    @JsonIgnore
    @Column(name = "password", unique = true)
    private String password;

    /**
     * The date when the user account stops working.
     */
    @Column(name = "end_date")
    @FutureOrPresent
    private Date endDate;

    /**
     * The roles of the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> authorities;

    /**
     * The no args constructor.
     */
    public User() {
        super();
        authorities = new HashSet<>();
    }

    /**
     * The constructor without the end date.
     *
     * @param fullName    the user full name
     * @param username       the username
     * @param password    the password
     * @param authorities the user granted authorities
     */
    public User(String fullName, String username, String password, Set<Role> authorities) {
        this.fullName = fullName;
        this.username = username;
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
