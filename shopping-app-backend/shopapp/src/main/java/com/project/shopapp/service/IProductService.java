package com.project.shopapp.service;

import com.project.shopapp.dto.request.ProductDTORequest;
import com.project.shopapp.dto.response.ProductDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {

    ProductDTOResponse createProduct(ProductDTORequest productDTORequest);

    ProductDTOResponse getProductById(long id);

    Page<ProductDTOResponse> getAllProduct(PageRequest pageRequest);

    ProductDTOResponse updateProduct(long id, ProductDTORequest productDTORequest);

    ProductDTOResponse deleteProduct(long id);

    boolean existsByName(String name);
}
