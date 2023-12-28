package com.project.shopapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "price", nullable = false)
    Float price;

    @Column(name = "thumbnail", length = 350)
    @ColumnDefault("''")
    String thumbnail;

    @Column(name = "description")
    @ColumnDefault("''")
    String description;


    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;


}
