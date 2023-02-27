package com.example.springsecurity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleDto {

    private Long id;
    @NotEmpty
    private String name;
}
