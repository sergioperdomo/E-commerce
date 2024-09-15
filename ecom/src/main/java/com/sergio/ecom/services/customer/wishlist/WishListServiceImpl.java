package com.sergio.ecom.services.customer.wishlist;


import com.sergio.ecom.dto.WishListDto;
import com.sergio.ecom.entity.Product;
import com.sergio.ecom.entity.User;
import com.sergio.ecom.entity.WishList;
import com.sergio.ecom.repository.ProductRepository;
import com.sergio.ecom.repository.UserRepository;
import com.sergio.ecom.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;

    public WishListDto addProductToWishList(WishListDto wishListDto){
        Optional<Product> optionalProduct = productRepository.findById(wishListDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()){
            WishList wishList = new WishList();

            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).getWishListDto();
        }
        return null;
    }

}
