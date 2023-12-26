package com.project.shopapp.controller;

import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exception.ApiRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    @PostMapping
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
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
        return ResponseEntity.ok("Create order detail " + orderDetailDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok("Get order detail " + id);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid @PathVariable("orderId") Long orderId
    ) {
        return ResponseEntity.ok("Get order details from order " + orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody OrderDetailDTO newOrderDetailData,
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
        return ResponseEntity.ok("Updated order detail " + newOrderDetailData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok("Deleted order detail " + id);
    }
}
