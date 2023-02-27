package com.example.springsecurity.converter;

import com.example.springsecurity.dto.UserCreateDto;
import com.example.springsecurity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        RoleConverter.class
})
public interface UserCreateConverter {

    UserCreateDto toDto(User user);

    User toEntity(UserCreateDto userCreateDto);
}
