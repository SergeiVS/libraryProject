package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(int id);

    Boolean existsById(int id);

    Optional<User> findByUserLogin(String userLogin);

    Boolean existsByUserLogin(String userLogin);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);

}
