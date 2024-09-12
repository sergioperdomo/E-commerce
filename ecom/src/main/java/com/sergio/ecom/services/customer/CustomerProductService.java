package com.sergio.ecom.services.customer;

import com.sergio.ecom.dto.ProductDetailDto;
import com.sergio.ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> searchProductByTitle(String title);
    List<ProductDto> getAllProducts();
    ProductDetailDto getProductDetailById(Long productId);
}
