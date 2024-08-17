package com.sergio.ecom.services.admin.adminproduct;

import com.sergio.ecom.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct (ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts ();

    List<ProductDto> getAllProductByName (String name);

    boolean deleteProduct(Long id);

}
