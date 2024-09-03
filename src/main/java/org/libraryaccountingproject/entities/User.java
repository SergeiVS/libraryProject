package org.libraryaccountingproject.entities;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public enum UserRole {
        ADMIN,
        READER,
        LIBRARIAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank
    @Column(nullable = false)
    private String firstName;
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotBlank
    @Column(unique = true)
    private String userLogin;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String password;
    @NotBlank
    @Email
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass() : o.getClass();
        if (oEffectiveClass != thisEffectiveClass) return false;
        User user = (User) o;
        return getUserId() != null && Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass().hashCode() : getUserId().hashCode();
    }


}
