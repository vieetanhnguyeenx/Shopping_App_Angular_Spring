package com.project.shopapp.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "fullname", length = 100)
    @ColumnDefault("''")
    String fullName;

    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    String phoneNumber;

    @Column(name = "address", length = 200)
    @ColumnDefault("''")
    String address;

    @Column(name = "password", nullable = false, length = 100)
    @ColumnDefault("''")
    String password;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    Boolean active;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "facebook_account_id")
    @ColumnDefault("0")
    int facebookAccountId;

    @Column(name = "google_account_id")
    @ColumnDefault("0")
    int googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
