package com.project.shopapp.controller;

import com.project.shopapp.dto.OrderDTO;
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
            @RequestBody @Valid OrderDTO orderDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }
        return ResponseEntity.ok("Create order " + orderDTO);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrdersById(@Valid @PathVariable("user_id") Long id) {
        return ResponseEntity.ok("Orders from user id " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable("id") Long id,
            @RequestBody @Valid OrderDTO orderDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }

        return ResponseEntity.ok("Updated order " + orderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable("id") Long id) {

        //TODO: update active to false
        return ResponseEntity.ok("Deleted order " + id);
    }
}