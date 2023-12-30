package com.project.shopapp.dto.response;

import com.project.shopapp.entity.Category;
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
public class ProductDTOResponse {
    Long id;
    String name;
    Float price;
    String thumbnail;
    String description;
    Category category;
}
