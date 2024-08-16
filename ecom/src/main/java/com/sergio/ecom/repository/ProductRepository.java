package com.sergio.ecom.repository;


import com.sergio.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {

    // Este método nos permite encontrar el producto por el nombre
    List<Product> findAllByNameContaining(String title);

}
