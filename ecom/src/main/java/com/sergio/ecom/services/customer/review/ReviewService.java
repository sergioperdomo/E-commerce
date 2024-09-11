package com.sergio.ecom.services.customer.review;

import com.sergio.ecom.dto.OrderedProductsResponseDto;
import com.sergio.ecom.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
