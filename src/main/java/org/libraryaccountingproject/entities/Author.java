package org.libraryaccountingproject.entities;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Entity
@Table(name = "author")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Firstname could not be empty")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;

    @NotBlank(message = "Lastname could not be empty")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getClass() : o.getClass();
        Class<?> thisEffectiveCLass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass() : this.getClass();
        if (thisEffectiveCLass != oEffectiveClass) return false;
        Author author = (Author) o;
        return getId() != 0 && Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass().hashCode() : this.getClass().hashCode();
    }
}
