package com.project.shopapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    @NotBlank(message = "Category name is required")
    @Length(min = 3, max = 200, message = "Category name must in range 3 to 200 character")
    String name;
}
