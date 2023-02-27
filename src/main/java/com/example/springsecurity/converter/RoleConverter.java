package com.example.springsecurity.converter;

import com.example.springsecurity.dto.RoleDto;
import com.example.springsecurity.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter {

    Role toEntity(RoleDto roleDto);

    RoleDto toDto(Role role);
}
