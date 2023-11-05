package ru.kotb.accounting_system.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


/**
 * The entity class that describes the user roles to access the API.
 */
@Data
@NoArgsConstructor
@Entity
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
