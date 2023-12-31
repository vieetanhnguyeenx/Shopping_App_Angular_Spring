package com.project.shopapp.dto.response;

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
public class ProductImageDTOResponse {
    Long id;
    ProductDTOResponse product;
    String imageUrl;
}
