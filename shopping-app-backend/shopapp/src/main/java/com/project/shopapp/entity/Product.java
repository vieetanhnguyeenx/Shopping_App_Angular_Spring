package com.project.shopapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name", nullable = false, length = 350)
    String name;

    Float price;

    @Column(name = "thumbnail", length = 350)
    String thumbnail;

    @Column(name = "description")
    String description;


    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;


}
