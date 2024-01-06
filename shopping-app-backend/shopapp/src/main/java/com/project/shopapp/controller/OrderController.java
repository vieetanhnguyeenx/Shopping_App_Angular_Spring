package com.project.shopapp.controller;

import com.project.shopapp.dto.request.OrderDTORequest;
import com.project.shopapp.dto.response.OrderDTOResponse;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.service.impl.OrderService;
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
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    public OrderDTOResponse createOrder(
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
        return orderService.createOrder(orderDTORequest);
    }

    @GetMapping("/{user_id}")
    public List<OrderDTOResponse> getOrdersById(@Valid @PathVariable("user_id") Long id) {
        return orderService.getAllOrderByUserId(id);
    }

    @PutMapping("/{id}")
    public OrderDTOResponse updateOrder(
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

        return orderService.updateOrder(id, orderDTORequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable("id") Long id) {

        //TODO: update active to false
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Silent deleted order " + id);
    }
}
