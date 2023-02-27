package com.example.springsecurity.service.impl;

import com.example.springsecurity.annotation.AspectLog;
import com.example.springsecurity.converter.UserConverter;
import com.example.springsecurity.converter.UserCreateConverter;
import com.example.springsecurity.converter.UserUpdateConverter;
import com.example.springsecurity.dto.UserCreateDto;
import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.dto.UserUpdateDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCreateConverter userCreateConverter;
    private final UserConverter userConverter;
    private final UserUpdateConverter userUpdateConverter;
    private final PasswordEncoder encoder;

    @Override
    @AspectLog
    public List<UserDto> getAll() {
        return convertList(userRepository.findAll());
    }

    @Override
    @AspectLog
    public UserDto findByUsername(String username) {
        return userConverter.toDto(userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User doe's not exist")));
    }

    @Override
    @AspectLog
    public UserDto save(UserCreateDto user) {
        if (user.getPassword() != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userConverter.toDto(userRepository.save(userCreateConverter.toEntity(user)));
    }

    @Override
    @AspectLog
    public UserDto getOne(Long id) {
        return userConverter.toDto(
                userRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Not found user by id: " + id)));
    }

    @Override
    @AspectLog
    public UserDto update(UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("Not found user by id: " + userUpdateDto.getId()));
        user.setName(userUpdateDto.getName());
        user.setLastName(userUpdateDto.getLastName());
        user.setUsername(userUpdateDto.getUsername());
        return userConverter.toDto(userRepository.save(user));
    }

    @Override
    @AspectLog
    public void remove(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    @AspectLog
    public void removeFromUsersRoles(Long userId) {
        userRepository.removeRoleWithUser(userId);
    }

    private List<UserDto> convertList(List<User> userList) {
        return userList.stream().map(userConverter::toDto).collect(Collectors.toList());
    }
}
