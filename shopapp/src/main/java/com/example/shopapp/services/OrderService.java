package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.OrderStatus;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.OrderRepository;
import com.example.shopapp.repositories.UserRepository;
import com.example.shopapp.responses.OrderResponse;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private static ModelMapper modelMapper;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        //Kiểm tra user có tồn tại hay không
            User user = userRepository.findById(orderDTO.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDTO.getUserId()));
            Order order = new Order();
            modelMapper.map(orderDTO, order);
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setStatus(OrderStatus.PENDING);
            Date shippingDate = orderDTO.getShippingDate() == null? new Date() : orderDTO.getShippingDate();
            if( shippingDate.before(new Date())){
                throw new DataNotFoundException("Date must be at least today");
            }
            order.setActive(true);
            orderRepository.save(order);
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse getOrder(Long id) {
        return null;
    }

    @Override
    public OrderResponse updateOrder(Long is, OrderDTO order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderResponse> getAllOrders(Long userId) {
        return List.of();
    }
}
