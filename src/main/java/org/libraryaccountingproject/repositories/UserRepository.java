package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getUserEmail().equals(email))
                .findFirst();
    }

    default Optional<User> findByUserLogin(String login) {
        return findAll().stream()
                .filter(user-> user.getUserLogin().equals(login))
                .findFirst();
    }

    public List<User> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
}
