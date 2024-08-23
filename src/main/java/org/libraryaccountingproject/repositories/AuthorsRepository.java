package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long> {

    public Optional<Author> findByFullname(String firstName, String lastName);
}
