package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.domain.TicCoupon;
import org.linlinjava.litemall.db.domain.TicCouponUser;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CouponVerifyService {

    @Autowired
    private TicCouponUserService couponUserService;
    @Autowired
    private TicCouponService couponService;

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    public TicCoupon checkCoupon(Integer userId, Integer couponId, BigDecimal checkedGoodsPrice) {
    	TicCoupon coupon = couponService.findById(couponId);
        TicCouponUser couponUser = couponUserService.queryOne(userId, couponId);
        if (coupon == null || couponUser == null) {
            return null;
        }

        // 检查是否超期
        Short timeType = coupon.getCouTimeType();
        Short days = coupon.getCouDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            if (now.isBefore(coupon.getCouStartTime()) || now.isAfter(coupon.getCouEndTime())) {
                return null;
            }
        }
        else if(timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
            LocalDateTime expired = couponUser.getCosAddTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        }
        else {
            return null;
        }

        // 检测商品是否符合
        // TODO 目前仅支持全平台商品，所以不需要检测
        Short goodType = coupon.getCouGoodsType();
        if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
            return null;
        }

        // 检测订单状态
        Short status = coupon.getCouStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getCouMin()) == -1) {
            return null;
        }

        return coupon;
    }

}