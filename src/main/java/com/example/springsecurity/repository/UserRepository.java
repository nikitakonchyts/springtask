package com.example.springsecurity.repository;

import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"roles"})
    List<User> findAll();

    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = "DELETE FROM users_roles WHERE user_id = ?", nativeQuery = true)
    void removeRoleWithUser(Long userId);
}
