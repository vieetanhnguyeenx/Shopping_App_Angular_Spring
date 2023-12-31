package com.project.shopapp.service.impl;

import com.project.shopapp.dto.request.ProductDTORequest;
import com.project.shopapp.dto.response.ProductDTOResponse;
import com.project.shopapp.entity.Category;
import com.project.shopapp.entity.Product;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ModelMapper mapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;


    @Override
    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest) {
        Category category = getCategoryById(productDTORequest.getCategoryId());

        Product product = mapper.map(productDTORequest, Product.class);
        product.setCategory(category);

        product = productRepository.save(product);
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public ProductDTOResponse getProductById(long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Product with id " + id + "dose not exist")));

        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public Page<ProductDTOResponse> getAllProduct(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(ProductMapper::toProductDTOResponse);
    }

    @Override
    public ProductDTOResponse updateProduct(long id, ProductDTORequest productDTORequest) {
        if (!productRepository.existsById(id))
            throw ApiRequestException.notFound(List.of("Product with id " + id + "dose not exist"));

        Category category = getCategoryById(productDTORequest.getCategoryId());

        Product product = mapper.map(productDTORequest, Product.class);
        product.setCategory(category);

        product = productRepository.save(product);
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public ProductDTOResponse deleteProduct(long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Product with id " + id + "dose not exist")));

        productRepository.delete(product);
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    private Category getCategoryById(long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Category with id " + id + "dose not exist")));
    }
}
