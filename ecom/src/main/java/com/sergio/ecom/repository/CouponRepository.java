package com.sergio.ecom.repository;

import com.sergio.ecom.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // Método para verificar si el cupón existe.
    boolean existsByCode(String code);
}
