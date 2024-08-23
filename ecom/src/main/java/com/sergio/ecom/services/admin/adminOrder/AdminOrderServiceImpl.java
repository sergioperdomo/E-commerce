package com.sergio.ecom.services.admin.adminOrder;

import com.sergio.ecom.dto.OrderDto;
import com.sergio.ecom.entity.Order;
import com.sergio.ecom.enums.OrderStatus;
import com.sergio.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    private final OrderRepository orderRepository;

    // Método para obtener los pedidos para el administrador
    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

}
