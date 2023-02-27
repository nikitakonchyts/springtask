package com.example.springsecurity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 4)
    private String password;
}
