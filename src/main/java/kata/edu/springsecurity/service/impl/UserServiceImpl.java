package kata.edu.springsecurity.service.impl;

import kata.edu.springsecurity.entity.User;
import kata.edu.springsecurity.repository.UserRepository;
import kata.edu.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User doe's not exist"));
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user by id: " + id));
    }

    @Override
    public User update(Long id, String name, String lastName) {
        if (id > 0) {
            if (userRepository.existsById(id)) {
                User user = getOne(id);
                user.setName(name);
                user.setLastName(lastName);
                return user;
            } else {
                throw new EntityNotFoundException("Not found user by id: " + id);
            }
        } else {
            throw new IllegalArgumentException("Ну короче, id не может быть меньше нуля, и кстати равняться нулю тоже");
        }
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
