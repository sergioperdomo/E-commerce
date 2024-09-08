package com.sergio.ecom.services.customer.review;

import com.sergio.ecom.dto.OrderedProductsResponseDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
