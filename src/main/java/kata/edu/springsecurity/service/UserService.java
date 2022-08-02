package kata.edu.springsecurity.service;

import kata.edu.springsecurity.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findByUsername(String username);

    User save(User user);

    User getOne(Long id);

    User update(Long id, String name, String lastName);

    void remove(Long id);
}
