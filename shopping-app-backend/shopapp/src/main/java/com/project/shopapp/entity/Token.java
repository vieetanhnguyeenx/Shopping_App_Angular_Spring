package com.project.shopapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "token", nullable = false, unique = true)
    String token;

    @Column(name = "token_type", nullable = false, length = 50)
    String tokenType;

    @Column(name = "expiration_date")
    LocalDateTime expirationDate;

    boolean revoked;
    boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
