package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.libraryaccountingproject.entities.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    boolean existsById(Integer id);

    List<Book> findByTitleContaining(String title);

    List<Book> findBySubject(BookSubject subject);


    List<Book> findByAuthorsId(Integer id);


   List<Book> findByStatus(Book.BookStatus status);

   List<Book> findByCodeISBN(String isbn);


}
