package com.sergio.ecom.services.admin.coupon;

import com.sergio.ecom.entity.Coupon;
import com.sergio.ecom.exceptions.ValidationException;
import com.sergio.ecom.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements  AdminCouponService {

    private final CouponRepository couponRepository;

    // Método para crear el coupon
    public Coupon createCoupon(Coupon coupon){
        if (couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists");
        }
        return couponRepository.save(coupon);
    }

    // Métodos para obtener los cupones
    public List<Coupon> getAllCoupon(){
        return couponRepository.findAll();
    }

}
