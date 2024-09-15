package com.sergio.ecom.services.customer.wishlist;

import com.sergio.ecom.dto.WishListDto;

import java.util.List;

public interface WishListService {

    WishListDto addProductToWishList(WishListDto wishListDto);
    List<WishListDto> getWishListByUserId(Long userId);
}
