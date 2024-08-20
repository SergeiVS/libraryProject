package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String mainTitle;
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    @ManyToOne
    @JoinColumn(name = "book_subject")
    private BookSubject subject;
    private Integer publisherId;
    private LocalDate yearOfIssue;
    private Integer numberOfPages;
    private String codeISBN;
    private String descriptionURL;
    private String imageURL;
    @Enumerated(EnumType.STRING)
    private BookStatus status;


}
