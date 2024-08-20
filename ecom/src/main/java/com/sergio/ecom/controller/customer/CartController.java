package com.sergio.ecom.controller.customer;

import com.sergio.ecom.dto.AddProductInCartDto;
import com.sergio.ecom.dto.OrderDto;
import com.sergio.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto ) {
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId) {
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

}
