package com.example.springsecurity.entity;

import com.example.springsecurity.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_roles_to_users")
    )
    private List<User> userList;

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return getId().equals(role.getId()) && getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 31 * hash + getId().intValue();
        hash = 31 * hash + (getName() == null ? 0 : getName().hashCode());
        return hash;
    }
}
