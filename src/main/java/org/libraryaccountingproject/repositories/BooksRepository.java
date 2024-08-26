package org.libraryaccountingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.libraryaccountingproject.entities.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

public default List<Book> findByPartTitle(String partTitle){
return findAll().stream()
        .filter(book -> book.getTitle().toLowerCase().contains(partTitle))
        .toList();
};
}
