package com.project.shopapp.controller;

import com.project.shopapp.dto.request.ProductDTORequest;
import com.project.shopapp.dto.response.ProductDTOResponse;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.service.IProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("Get products " + page + " " + limit);
    }

    @GetMapping("/{id}")
    public ProductDTOResponse getProductById(@PathVariable("id") long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{id}")
    public ProductDTOResponse deleteProduct(@PathVariable("id") long productId) {
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
}
