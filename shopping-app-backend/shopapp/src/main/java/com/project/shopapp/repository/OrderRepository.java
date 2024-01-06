package com.project.shopapp.repository;

import com.project.shopapp.entity.Order;
import com.project.shopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
