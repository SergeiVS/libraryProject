package org.libraryaccountingproject.entities;

import jakarta.persistence.*;
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
    private String userLogin;
    private String password;
    private String userEmail;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
