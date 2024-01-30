package ru.kotb.accountingsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
     * The available role authorities for users.
     */
    public enum Authority {
        ADMIN("ADMIN"),
        USER("USER");

        private final String text;

        Authority(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * The authority of the role.
     */
    @Column(name = "authority", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Authority authority;

    /**
     * Returns the role authority.
     */
    public String getAuthority() {
        return authority.toString();
    }
}
