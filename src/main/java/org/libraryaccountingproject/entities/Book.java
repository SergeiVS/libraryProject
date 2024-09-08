package org.libraryaccountingproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    public enum BookStatus {
        AVAILABLE,
        NOT_AVAILABLE,
        OUT,
        ORDERED
    }

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

    @NotBlank(message = "ISBN could not be empty")
    @Size(min = 10, message = "ISBN could not be shorter as 10 digits")
    @ISBN(message = "ISBN must be in proper format")
    private String codeISBN;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private BookSubject subject;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @NotBlank
    @URL
    private String coverImageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass() : o.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Book book = (Book) o;
        return getBookId() != null && Objects.equals(getBookId(), book.getBookId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass().hashCode() : Objects.hash(getBookId());
    }


}
