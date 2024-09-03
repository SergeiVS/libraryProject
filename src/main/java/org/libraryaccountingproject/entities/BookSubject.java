package org.libraryaccountingproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Subject could not be empty")
    private String subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getClass() : o.getClass();
        Class<?> thisEffectiveclass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass() : o.getClass();
        if (thisEffectiveclass != oEffectiveClass) return false;
        BookSubject subject = (BookSubject) o;
        return getId() != null && Objects.equals(getId(), subject.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass().hashCode() : getId().hashCode();
    }
}
