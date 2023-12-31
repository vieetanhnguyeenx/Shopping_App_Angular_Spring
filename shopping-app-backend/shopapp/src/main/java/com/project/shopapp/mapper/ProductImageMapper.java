package com.project.shopapp.mapper;

import com.project.shopapp.dto.response.ProductImageDTOResponse;
import com.project.shopapp.entity.ProductImage;

public class ProductImageMapper {
    public static ProductImageDTOResponse toProductImageDTOResponse(ProductImage productImage) {
        return ProductImageDTOResponse.builder()
                .id(productImage.getId())
                .product(ProductMapper.toProductDTOResponse(productImage.getProduct()))
                .imageUrl(productImage.getImageUrl())
                .build();
    }
}
