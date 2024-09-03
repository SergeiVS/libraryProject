package org.libraryaccountingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.libraryaccountingproject.entities.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    boolean existsById(Long id);

    default List<Book> findByPartTitle(String partTitle) {
        return findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(partTitle))
                .toList();
    };

    default List<Book> findBySubjectName(String subjectName) {
        return findAll().stream()
                .filter(book -> book.getSubject().getSubject().toLowerCase().equals(subjectName))
                .toList();
    }

    default List<Book> findByAuthorId(Integer authorId) {
        return findAll().stream()
                .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getId() == authorId))
                .toList();
    }

    default List<Book> findByStatus(Book.BookStatus status) {
        return findAll().stream()
                .filter(book -> book.getStatus().equals(status))
                .toList();
    }

    default List<Book> findByISBN(String isbn) {
        return findAll().stream()
                .filter(book -> book.getCodeISBN().equals(isbn))
                .toList();
    }

}
