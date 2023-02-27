package com.example.springsecurity.service.impl;

import com.example.springsecurity.converter.RoleConverter;
import com.example.springsecurity.dto.RoleDto;
import com.example.springsecurity.entity.Role;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Override
    public List<RoleDto> getAll() {
        return convertList(roleRepository.findAll());
    }

    @Override
    public Role getOne(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("нет роли с таким id"));
    }

    @Override
    public void setRoleToUser(Long userId, String roleName) {
        List<Role> roleList = roleRepository.findAll();
        Long roleId = roleList.stream().filter(s -> s.getName()
                .equalsIgnoreCase(String.format("ROLE_%s", roleName))).findFirst().orElseThrow(
                () -> new EntityNotFoundException("нет такой роли")).getId();
        roleRepository.setRoleToUser(userId, roleId);
    }

    private List<RoleDto> convertList(List<Role> roleList) {
        return roleList.stream().map(roleConverter::toDto).collect(Collectors.toList());
    }
}
