package org.libraryaccountingproject.entities;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userLogin;
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String password;
    @Email
    @Column(unique = true)
    private String userEmail;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
