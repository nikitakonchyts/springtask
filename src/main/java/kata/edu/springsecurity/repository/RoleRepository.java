package kata.edu.springsecurity.repository;

import kata.edu.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query(value = "INSERT INTO users_roles (user_id, role_id) values (?1, ?2)", nativeQuery = true)
    void setRoleToUser( Long userId, Long roleId);

}
