package com.project.shopapp.entity;

import com.project.shopapp.common.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "fullname", length = 100)
    @ColumnDefault("''")
    String fullName;

    @Column(name = "email", length = 100)
    @ColumnDefault("''")
    String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    String phoneNumber;

    @Column(name = "address", nullable = false, length = 200)
    String address;

    @Column(name = "note", length = 100)
    @ColumnDefault("''")
    String note;

    @Column(name = "order_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime orderDate;

    OrderStatus status;

    @Column(name = "total_money")
    Float totalMoney;

    @Column(name = "shipping_method", length = 100)
    String shippingMethod;

    @Column(name = "shipping_address", length = 200)
    String shippingAddress;

    @Column(name = "shipping_date")
    LocalDate shippingDate;

    @Column(name = "tracking_number", length = 100)
    String trackingNumber;

    @Column(name = "payment_method", length = 100)
    String paymentMethod;

    @Column(name = "active")
    @ColumnDefault("1")
    Boolean active;
}