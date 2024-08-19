package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String mainTitle;
//    private String[] subTitles;
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    private Subject subject;
    private Integer publisherId;
    private LocalDate yearOfIssue;
    private Integer numberOfPages;
    private String codeISBN;
    private String descriptionURL;
    private String imageURL;
    private BookStatus status;


}
