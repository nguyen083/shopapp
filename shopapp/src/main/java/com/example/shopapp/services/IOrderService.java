package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;

    OrderResponse getOrder(Long id);

    OrderResponse updateOrder(Long is, OrderDTO order);
    void deleteOrder(Long id);
    List<OrderResponse> getAllOrders (Long userId);
}
