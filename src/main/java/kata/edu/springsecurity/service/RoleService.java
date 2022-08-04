package kata.edu.springsecurity.service;

import kata.edu.springsecurity.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();

    Role getOne(Long id);

    void setRoleToUser(Long userId, String role);
}
