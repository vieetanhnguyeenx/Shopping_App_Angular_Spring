package com.project.shopapp.mapper;

import com.project.shopapp.dto.response.CategoryDTOResponse;
import com.project.shopapp.entity.Category;

public class CategoryMapper {
    public static CategoryDTOResponse toCategoryDTOResponse(Category category) {
        return CategoryDTOResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
