package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long> {




    default Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) {

        return findAll().stream()
                .filter(author -> author.getFirstName().toLowerCase().contains(firstName))
                .filter(author -> author.getLastName().toLowerCase().contains(lastName))
                .findFirst();
    };

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);
}
