package com.sergio.ecom.controller.customer;

import com.sergio.ecom.dto.WishListDto;
import com.sergio.ecom.services.customer.wishlist.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto){
        WishListDto postedWishListDto = wishListService.addProductToWishList(wishListDto);

        if (postedWishListDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishListDto);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishListDto>> getWishListByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(wishListService.getWishListByUserId(userId));
    }
}
