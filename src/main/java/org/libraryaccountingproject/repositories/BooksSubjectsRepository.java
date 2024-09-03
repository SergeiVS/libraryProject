package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BooksSubjectsRepository extends JpaRepository<BookSubject, Integer> {

    boolean existsById(Integer id);

    Optional<BookSubject> findById(Integer id);

    boolean existsBySubject(String subject);

    Optional<BookSubject> findBySubject(String subject);
}
