package com.project.shopapp.dto.response;

import com.project.shopapp.common.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTOResponse {
    Long id;
    Long userId;
    String fullName;
    String email;
    String phoneNumber;
    String address;
    String note;
    LocalDateTime orderDate;
    OrderStatus status;
    Float totalMoney;
    String shippingMethod;
    String shippingAddress;
    LocalDate shippingDate;
    String trackingNumber;
    String paymentMethod;
    Boolean active;
}
