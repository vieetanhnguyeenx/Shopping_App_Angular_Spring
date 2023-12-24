package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
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
public class ProductDTO {

    @NotBlank(message = "Product name is required")
    @Length(min = 3, max = 200, message = "Product name must in range 3 to 200 character")
    String name;

    @NotNull(message = "Product price is required")
    @Min(value = 0, message = "Product name must be greater than or equal to 0")
    @Max(value = 100000000, message = "Product name must be greater than or equal to 100,000,000")
    Float price;

    String thumbnail;

    String description;


    @NotNull(message = "Product category is required")
    @JsonProperty("category_id")
    Integer categoryId;

    List<MultipartFile> files;


    public void setCategory_id(@NotNull(message = "Product category is required") Integer id) {
        this.categoryId = id;
    }
}
