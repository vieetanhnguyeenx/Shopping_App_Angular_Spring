package com.project.shopapp.controller;

import com.github.javafaker.Faker;
import com.project.shopapp.dto.request.ProductDTORequest;
import com.project.shopapp.dto.response.PageProductDTOResponse;
import com.project.shopapp.dto.response.ProductDTOResponse;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.service.IProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProductService productService;

    @GetMapping
    public ResponseEntity<PageProductDTOResponse> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<ProductDTOResponse> responses = productService.getAllProduct(pageRequest);

        return ResponseEntity.ok(PageProductDTOResponse.builder()
                .products(responses.getContent())
                .totalPages(responses.getTotalPages())
                .build());
    }

    @GetMapping("/{id}")
    public ProductDTOResponse getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{id}")
    public ProductDTOResponse deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTORequest productDTORequest,
            BindingResult result) {

        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }

        return ResponseEntity.ok(productService.createProduct(productDTORequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody ProductDTORequest productDTORequest,
            BindingResult result) {

        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }

        return ResponseEntity.ok(productService.updateProduct(id, productDTORequest));
    }

    @PostMapping("/generateFakeProducts")
    public ResponseEntity<String> generateFakeProducts() {
        Faker faker = new Faker();
        for (int i = 0; i < 5000; i++) {
            String productName = faker.commerce().productName();
            if (productService.existsByName(productName))
                continue;
            ProductDTORequest productDTORequest = ProductDTORequest.builder()
                    .name(productName)
                    .price((float) faker.number().randomDouble(2, 1000, 100000000))
                    .description(faker.lorem().sentence())
                    .thumbnail("")
                    .categoryId(faker.number().numberBetween(Long.valueOf(3), Long.valueOf(6)))
                    .build();
            productService.createProduct(productDTORequest);
        }
        return ResponseEntity.ok("Fake products");
    }
}
