package kata.edu.springsecurity.service.impl;

import kata.edu.springsecurity.entity.Role;
import kata.edu.springsecurity.repository.RoleRepository;
import kata.edu.springsecurity.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getOne(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("нет роли с таким id"));
    }

    @Override
    public void setRoleToUser(Long userId, String roleName) {
        List<Role> roleList = roleRepository.findAll();
        Long roleId = roleList.stream().filter(s -> s.getName().equalsIgnoreCase(roleName)).findFirst().orElseThrow(
                () -> new EntityNotFoundException("нет такой роли")).getId();
        roleRepository.setRoleToUser(userId, roleId);
    }
}
