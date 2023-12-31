package com.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageDTORequest {
    @JsonProperty("product_id")
    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Product ID must be greater than 0")
    Long productId;

    List<MultipartFile> files;

    public void setProduct_id(
            @NotNull(message = "Product id is required")
            @Min(value = 1, message = "Product ID must be greater than 0")
                    Long id
    ) {
        this.productId = id;
    }
}
