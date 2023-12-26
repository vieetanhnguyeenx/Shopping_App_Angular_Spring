package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    @Min(value = 1, message = "User's ID must be greater than 0")
    Long userId;

    @JsonProperty("fullname")
    String fullName;

    @Email(message = "Email must be abc@xyz.cdf")
    String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    String phoneNumber;

    @NotBlank(message = "Address is required")
    String address;

    String note;

    @JsonProperty("total_money")
    @NotNull(message = "Total money is required")
    @Min(value = 0, message = "Total money must be greater or equal to 0")
    Float totalMoney;

    @JsonProperty("shipping_method")
    String shippingMethod;

    @JsonProperty("shipping_address")
    String shippingAddress;

    @JsonProperty("payment_method")
    String paymentMethod;
}
