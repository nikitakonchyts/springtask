package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.dto.UserCreateDto;
import com.example.springsecurity.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto findByUsername(String username);

    UserDto save(UserCreateDto userCreateDto);

    UserDto getOne(Long id);

    UserDto update(UserUpdateDto userUpdateDto);

    void remove(Long id);

    void removeFromUsersRoles(Long userId);
}
