package org.libraryaccountingproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirmation")
public class ConformationMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String codeMessage;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false )
    private User user;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
}
