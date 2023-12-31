package com.project.shopapp.service.impl;

import com.project.shopapp.dto.response.ProductImageDTOResponse;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.ProductImage;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.mapper.ProductImageMapper;
import com.project.shopapp.repository.ProductImageRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.service.IProductImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageService implements IProductImageService {
    ProductRepository productRepository;
    ProductImageRepository productImageRepository;

    @Override
    public List<ProductImageDTOResponse> createProductImages(Long productId, List<String> files) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> ApiRequestException.notFound(List.of("Product with id " + productId + "dose not exist")));
        List<ProductImage> images = new ArrayList<>();
        for (String name : files) {
            images.add(ProductImage.builder()
                    .imageUrl(name)
                    .product(product)
                    .build());
        }

        return productImageRepository.saveAll(images).stream()
                .map(ProductImageMapper::toProductImageDTOResponse)
                .collect(Collectors.toList());
    }
}
