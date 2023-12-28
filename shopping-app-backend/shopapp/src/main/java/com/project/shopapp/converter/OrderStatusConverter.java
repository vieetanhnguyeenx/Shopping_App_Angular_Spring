package com.project.shopapp.converter;

import com.project.shopapp.common.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }

        return orderStatus.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getCode() == code)
                .findFirst()
                .orElseThrow(AssertionError::new);
    }
}
