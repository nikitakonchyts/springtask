package kata.edu.springsecurity.entity;

import kata.edu.springsecurity.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends BaseEntity implements UserDetails, Serializable {

    @Column(name = "last_name")
    private String lastName;

    private String username;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "non_expired")
    private Boolean isAccountNonExpired;

    @Column(name = "non_locked")
    private Boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "fk_users_to_roles")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @PrePersist
    public void defaultSettings() {
        if (isAccountNonLocked == null) {
            isAccountNonLocked = true;
        }
        if (isCredentialsNonExpired == null) {
            isCredentialsNonExpired = true;
        }
        if (isAccountNonExpired == null) {
            isAccountNonExpired = true;
        }
        if (isEnabled == null) {
            isEnabled = true;
        }
    }
}
