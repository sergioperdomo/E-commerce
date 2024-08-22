package com.sergio.ecom.services.customer.cart;

import com.sergio.ecom.dto.AddProductInCartDto;
import com.sergio.ecom.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);
    OrderDto applyCoupon(Long userId, String code);
}
