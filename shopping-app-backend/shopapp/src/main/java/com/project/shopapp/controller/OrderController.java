package com.project.shopapp.controller;

import com.project.shopapp.dto.request.OrderDTORequest;
import com.project.shopapp.exception.ApiRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid OrderDTORequest orderDTORequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }
        return ResponseEntity.ok("Create order " + orderDTORequest);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrdersById(@Valid @PathVariable("user_id") Long id) {
        return ResponseEntity.ok("Orders from user id " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable("id") Long id,
            @RequestBody @Valid OrderDTORequest orderDTORequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }

        return ResponseEntity.ok("Updated order " + orderDTORequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable("id") Long id) {

        //TODO: update active to false
        return ResponseEntity.ok("Deleted order " + id);
    }
}
