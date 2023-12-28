package com.project.shopapp.dto.request;

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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTORequest {

    @NotBlank(message = "Category name is required")
    @Length(min = 3, max = 200, message = "Category name must in range 3 to 200 character")
    String name;

}
