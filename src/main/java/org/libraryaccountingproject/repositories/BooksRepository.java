package org.libraryaccountingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.libraryaccountingproject.entities.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

}
