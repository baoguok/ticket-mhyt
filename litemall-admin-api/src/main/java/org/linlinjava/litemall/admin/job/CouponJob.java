package org.linlinjava.litemall.admin.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.db.service.TicCouponService;
import org.linlinjava.litemall.db.service.TicCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 检测优惠券过期情况
 */
@Component
public class CouponJob {
    private final Log logger = LogFactory.getLog(CouponJob.class);

    @Autowired
    private TicCouponService couponService;
    @Autowired
    private TicCouponUserService couponUserService;

    /**
     * 每隔一个小时检查
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void checkCouponExpired() {
        logger.info("系统开启任务检查优惠券是否已经过期");

//        List<LitemallCoupon> couponList = couponService.queryExpired();
//        for(LitemallCoupon coupon : couponList){
//            coupon.setStatus(CouponConstant.STATUS_EXPIRED);
//            couponService.updateById(coupon);
//        }
//
//        List<LitemallCouponUser> couponUserList = couponUserService.queryExpired();
//        for(LitemallCouponUser couponUser : couponUserList){
//            couponUser.setStatus(CouponUserConstant.STATUS_EXPIRED);
//            couponUserService.update(couponUser);
//        }
    }

}
