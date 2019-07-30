package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.domain.TicCoupon;
import org.linlinjava.litemall.db.domain.TicCouponUser;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignService {

    @Autowired
    private TicCouponUserService couponUserService;
    @Autowired
    private TicCouponService couponService;

    /**
     * 分发注册优惠券
     *
     * @param userId
     * @return
     */
    public void assignForRegister(Integer userId) {
        List<TicCoupon> couponList = couponService.queryRegister();
        for(TicCoupon coupon : couponList){
            Integer couponId = coupon.getCouId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getCouLimit();
            while(limit > 0){
            	TicCouponUser couponUser = new TicCouponUser();
                couponUser.setCosCouponId(couponId);
                couponUser.setCosUserId(userId);
                Short timeType = coupon.getCouTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setCosStartTime(coupon.getCouStartTime());
                    couponUser.setCosEndTime(coupon.getCouEndTime());
                }
                else{
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setCosStartTime(now);
                    couponUser.setCosEndTime(now.plusDays(coupon.getCouDays()));
                }
                couponUserService.add(couponUser);

                limit--;
            }
        }

    }

}