package org.libraryaccountingproject.entities;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account")
public class User {


    public enum UserState {
        REGISTERED,
        CONFIRMED,
        RESTRICTED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
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
    private String email;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserState userState;
    @OneToMany(mappedBy = "user")
    @Column(name = "confirmations")
    private Set<ConfirmationMessage> confirmationMessage;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass() : o.getClass();
        if (oEffectiveClass != thisEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getClass().hashCode() : getId().hashCode();
    }


}
