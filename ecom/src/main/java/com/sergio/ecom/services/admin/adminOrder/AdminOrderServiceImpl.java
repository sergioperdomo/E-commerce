package com.sergio.ecom.services.admin.adminOrder;

import com.sergio.ecom.dto.AnalyticsResponse;
import com.sergio.ecom.dto.OrderDto;
import com.sergio.ecom.entity.Order;
import com.sergio.ecom.enums.OrderStatus;
import com.sergio.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    public OrderDto changeOrderStatus (Long orderId, String status){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();


           if (Objects.equals(status, "Shipped")){
               order.setOrderStatus(OrderStatus.Shipped);
           } else if (Objects.equals(status,"Delivered")) {
               order.setOrderStatus(OrderStatus.Delivered);
           }
            return orderRepository.save(order).getOrderDto();
        }
        return null;
    }

     public AnalyticsResponse calculateAnalytics(){
         LocalDate currentDate = LocalDate.now();
         LocalDate previousMothDate = currentDate.minusMonths(1);

         Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
         Long previousMonthOrders = getTotalOrdersForMonth(previousMothDate.getMonthValue(),previousMothDate.getYear());

         Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
         Long previousMonthEarnings = getTotalEarningsForMonth(previousMothDate.getMonthValue(),previousMothDate.getYear());

         Long placed = orderRepository.countByOrderStatus(OrderStatus.Placed);
         Long shipped = orderRepository.countByOrderStatus(OrderStatus.Shipped);
         Long delivered = orderRepository.countByOrderStatus(OrderStatus.Delivered);

         return new AnalyticsResponse(placed, shipped, delivered, currentMonthOrders, previousMonthOrders, currentMonthEarnings, previousMonthEarnings);
     }

     public Long getTotalOrdersForMonth(int month, int year){
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, year);
         calendar.set(Calendar.MONTH, month - 1);
         calendar.set(Calendar.DAY_OF_MONTH, 1);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);

         Date startOfMonth = calendar.getTime();

         // Move the calendar to the end of the specified month
         calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
         calendar.set(Calendar.HOUR_OF_DAY, 23);
         calendar.set(Calendar.MINUTE, 59);
         calendar.set(Calendar.SECOND, 59);

         Date endOfMonth = calendar.getTime();

         List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

         return (long) orders.size();
     }


     public Long getTotalEarningsForMonth(int month, int year){
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, year);
         calendar.set(Calendar.MONTH, month - 1);
         calendar.set(Calendar.DAY_OF_MONTH, 1);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);

         Date startOfMonth = calendar.getTime();

         // Move the calendar to the end of the specified month
         calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
         calendar.set(Calendar.HOUR_OF_DAY, 23);
         calendar.set(Calendar.MINUTE, 59);
         calendar.set(Calendar.SECOND, 59);

         Date endOfMonth = calendar.getTime();

         List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

         Long sum = 0L;
         for (Order order: orders){
             sum += order.getAmount();
         }
         return sum;
     }

}
