package com.project.shopapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "social_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "provider", nullable = false, length = 20)
    String provider;

    @Column(name = "provider_id", nullable = false, length = 50)
    String providerId;

    @Column(name = "email", nullable = false, length = 150)
    String email;

    @Column(name = "name", nullable = false, length = 150)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
