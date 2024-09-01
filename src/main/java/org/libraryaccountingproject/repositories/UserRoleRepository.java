package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
     Optional<UserRole> findByRoleName(String roleName);

     default Boolean existsByRoleName(String roleName) {
        return findByRoleName(roleName).isPresent();
    }
}
