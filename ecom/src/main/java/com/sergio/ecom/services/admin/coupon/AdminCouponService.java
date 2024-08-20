package com.sergio.ecom.services.admin.coupon;

import com.sergio.ecom.entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupon();

}
