package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BooksSubjectsRepository extends JpaRepository<BookSubject, Long> {

    public default Optional<BookSubject> findBySubject(String subject) {
        return findAll().stream()
                .filter(objectSubject -> objectSubject.getSubject().toLowerCase().equals(subject))
                .findFirst();
    }

    ;
}
