package com.example.springsecurity.service;

import com.example.springsecurity.dto.RoleDto;
import com.example.springsecurity.entity.Role;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    Role getOne(Long id);

    void setRoleToUser(Long userId, String role);
}
