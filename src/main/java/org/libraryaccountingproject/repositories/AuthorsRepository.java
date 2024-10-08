package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {


    Boolean existsById(int id);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

   void deleteById(int id);

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);
}
