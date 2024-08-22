package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksSubjectsRepository extends JpaRepository<BookSubject, Long> {
}
