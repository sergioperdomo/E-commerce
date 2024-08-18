package com.sergio.ecom.services.customer.cart;

import com.sergio.ecom.dto.AddProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
}
