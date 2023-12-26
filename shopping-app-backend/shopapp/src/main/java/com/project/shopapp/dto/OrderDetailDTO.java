package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class OrderDetailDTO {

    @JsonProperty("order_id")
    @NotNull(message = "Order ID is required")
    @Min(value = 1, message = "Order ID must be greater than 0")
    Long orderId;

    @JsonProperty("product_id")
    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Product ID must be greater than 0")
    Long productId;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater or equal to 0")
    Float price;

    @JsonProperty("number_of_products")
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    Integer numberOfProducts;

    @JsonProperty("total_money")
    @NotNull(message = "Total money is required")
    @Min(value = 0, message = "Total money must be greater or equal to 0")
    Float totalMoney;

    String color;
}
