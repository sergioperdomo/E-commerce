package com.sergio.ecom.services.admin.adminOrder;

import com.sergio.ecom.dto.AnalyticsResponse;
import com.sergio.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus (Long orderId, String status);

    AnalyticsResponse calculateAnalytics();

}
