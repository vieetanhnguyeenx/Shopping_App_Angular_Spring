package com.project.shopapp.service.impl;

import com.project.shopapp.dto.request.OrderDTORequest;
import com.project.shopapp.dto.response.OrderDTOResponse;
import com.project.shopapp.entity.Order;
import com.project.shopapp.entity.User;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.mapper.OrderMapper;
import com.project.shopapp.repository.OrderRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    ModelMapper mapper;
    UserRepository userRepository;
    OrderRepository orderRepository;

    @Override
    public OrderDTOResponse createOrder(OrderDTORequest orderDTORequest) {
        // check user id exist
        User user = getUserById(orderDTORequest.getUserId());

        Order order = mapper.map(orderDTORequest, Order.class);
        order.setUser(user);
        order = orderRepository.save(order);
        return OrderMapper.toOrderDTOResponse(order);
    }

    @Override
    public OrderDTOResponse getOrder(Long id) {
        Order order = getOrderById(id);
        return OrderMapper.toOrderDTOResponse(order);
    }


    @Override
    public OrderDTOResponse updateOrder(Long id, OrderDTORequest orderDTORequest) {
        if (!orderRepository.existsById(id))
            throw ApiRequestException.notFound(List.of("Order with id " + id + "dose not exist"));

        User user = getUserById(orderDTORequest.getUserId());
        Order order = mapper.map(orderDTORequest, Order.class);
        order.setUser(user);
        order.setId(id);
        return OrderMapper.toOrderDTOResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        order.setActive(false);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTOResponse> getAllOrderByUserId(Long userId) {
        User user = getUserById(userId);

        return orderRepository.findAllByUser(user)
                .stream()
                .map(OrderMapper::toOrderDTOResponse)
                .toList();
    }

    private User getUserById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("User with id " + id + " dose not exist")));
    }

    private Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Order with id " + id + "dose not exist")));
    }
}
