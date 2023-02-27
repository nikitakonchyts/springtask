package com.example.springsecurity.converter;

import com.example.springsecurity.dto.UserUpdateDto;
import com.example.springsecurity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUpdateConverter {

    UserUpdateDto toDto(User user);

    User toEntity(UserUpdateDto userUpdateDto);
}
