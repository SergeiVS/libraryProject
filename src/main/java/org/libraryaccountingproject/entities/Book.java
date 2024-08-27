package org.libraryaccountingproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @NotBlank(message = "Book title could not be empty")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonBackReference
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private BookSubject subject;

    @NotBlank(message = "ISBN could not be empty")
    @Size(min = 10, message = "ISBN could not be shorter as 10 digits")
    @ISBN(message = "ISBN must be in proper format")
    private String codeISBN;

    @Enumerated(EnumType.STRING)
    private BookStatus status;
}
