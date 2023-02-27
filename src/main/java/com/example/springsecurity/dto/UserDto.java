package com.example.springsecurity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String lastName;
    private String name;
    private String username;
    private Set<RoleDto> roles;
}
