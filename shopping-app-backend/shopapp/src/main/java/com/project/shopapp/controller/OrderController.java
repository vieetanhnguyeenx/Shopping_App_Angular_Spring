package com.project.shopapp.controller;

import com.project.shopapp.dto.OrderDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid OrderDTO orderDTO
    ) {
        return ResponseEntity.ok("Create order");
    }
}
