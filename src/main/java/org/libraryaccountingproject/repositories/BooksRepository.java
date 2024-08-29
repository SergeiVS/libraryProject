package org.libraryaccountingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.libraryaccountingproject.entities.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

default List<Book> findByPartTitle(String partTitle){
return findAll().stream()
        .filter(book -> book.getTitle().toLowerCase().contains(partTitle))
        .toList();
};

default List<Book> findBySubjectName(String subjectName){
    return findAll().stream()
            .filter(book -> book.getSubject().getSubject().toLowerCase().equals(subjectName))
            .toList();
}
}
