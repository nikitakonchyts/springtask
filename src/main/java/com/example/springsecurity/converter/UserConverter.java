package com.example.springsecurity.converter;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        RoleConverter.class
})
public interface UserConverter {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
