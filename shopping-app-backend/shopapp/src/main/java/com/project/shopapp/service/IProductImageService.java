package com.project.shopapp.service;

import com.project.shopapp.dto.response.ProductImageDTOResponse;

import java.util.List;

public interface IProductImageService {
    List<ProductImageDTOResponse> createProductImages(Long productId, List<String> files);
}
