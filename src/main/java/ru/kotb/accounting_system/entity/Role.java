package ru.kotb.accounting_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The entity class that describes the user roles to access the API.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends AbstractEntity implements GrantedAuthority {

    /**
     * The name of the role.
     */
    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    /**
     * Returns the role name.
     *
     * @return the role name
     */
    @Override
    public String getAuthority() {
        return authority;
    }
}
