package com.project.shopapp.mapper;

import com.project.shopapp.dto.response.ProductDTOResponse;
import com.project.shopapp.entity.Product;

public class ProductMapper {
    public static ProductDTOResponse toProductDTOResponse(Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .category(CategoryMapper.toCategoryDTOResponse(product.getCategory()))
                .build();
    }
}
