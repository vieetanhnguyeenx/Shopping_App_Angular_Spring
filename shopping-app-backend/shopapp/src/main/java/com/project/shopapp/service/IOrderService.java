package com.project.shopapp.service;

import com.project.shopapp.dto.request.OrderDTORequest;
import com.project.shopapp.dto.response.OrderDTOResponse;

import java.util.List;

public interface IOrderService {
    OrderDTOResponse createOrder(OrderDTORequest orderDTORequest);

    OrderDTOResponse getOrder(Long id);

    OrderDTOResponse updateOrder(Long id, OrderDTORequest orderDTORequest);

    void deleteOrder(Long id);

    List<OrderDTOResponse> getAllOrder(Long userId);

}
