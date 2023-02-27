package com.example.springsecurity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateDto {

    private Long id;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;
}
