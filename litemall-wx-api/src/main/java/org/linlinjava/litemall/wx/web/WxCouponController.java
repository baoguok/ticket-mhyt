package org.linlinjava.litemall.wx.web;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.CustomTicCouAndCos;
import org.linlinjava.litemall.db.domain.LitemallCart;
import org.linlinjava.litemall.db.domain.LitemallGrouponRules;
import org.linlinjava.litemall.db.domain.TicCoupon;
import org.linlinjava.litemall.db.domain.TicCouponUser;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.dao.CouponVo;
import org.linlinjava.litemall.wx.util.WxResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券服务
 */
@RestController
@RequestMapping("/wx/coupon")
@Validated
public class WxCouponController {
	private final Log logger = LogFactory.getLog(WxCouponController.class);

	@Autowired
	private TicCouponService couponService;
	@Autowired
	private TicCouponUserService couponUserService;
	@Autowired
	private TicCouponUserService couponuserService;
	/**
	 * 优惠券列表
	 *
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @return
	 */
	@GetMapping("list")
	public Object list(@RequestParam(defaultValue = "1") Integer page,
					   @RequestParam(defaultValue = "10") Integer size,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {

		List<TicCoupon> couponList = couponService.queryList(page, size, sort, order);
		int total = couponList.size();
		Map<String, Object> data = new HashMap<String, Object>();
      
		data.put("data", couponList);
		data.put("count", total);
		return ResponseUtil.ok(data);
	}

	/**
	 * 个人优惠券列表
	 *
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("mylist")
	public Object mylist(@LoginUser Integer userId,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		List<TicCouponUser> couUserList = couponUserService.queryList(userId, null, (short)0);
		System.out.println("优惠券信息: " + JSON.toJSON(couUserList));
//		List<TicCouponUser> couUserOutList = couponUserService.queryList(userId, null, (short)1);
		List<CouponVo> couponVoList = change(couUserList);
//		List<CouponVo> couponVoOutList = change(couUserOutList);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("couponVoList", couponVoList);
//		data.put("couponVoOutList", couponVoOutList);
		int count = couUserList.size();
		data.put("count", count);
		return ResponseUtil.ok(data);
	}

	private List<CouponVo> change(List<TicCouponUser> couponList) {
		List<CouponVo> couponVoList = new ArrayList<>(couponList.size());
		for (TicCouponUser couponUser : couponList) {
			Integer couponId = couponUser.getCosCouponId();
			TicCoupon coupon = couponService.findById(couponId);
			CouponVo couponVo = new CouponVo();
			couponVo.setId(coupon.getCouId());
			couponVo.setName(coupon.getCouName());
			couponVo.setDesc(coupon.getCouDesc());
			couponVo.setTag(coupon.getCouTag());
			couponVo.setMin(coupon.getCouMin().toPlainString());
			couponVo.setDiscount(coupon.getCouDiscount().toPlainString());
			couponVo.setStartTime(couponUser.getCosStartTime());
			couponVo.setEndTime(couponUser.getCosEndTime());

			couponVoList.add(couponVo);
		}

		return couponVoList;
	}

	/**
	 * 当前门票订单可用优惠券
	 *
	 * @param userId
	 * @return
	 */
	@GetMapping("selectlist")
	public Object selectlist(@LoginUser Integer userId, Integer catId,String gooIds) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		List<CustomTicCouAndCos> couponUsers = couponuserService.MayUseCoupon(userId);
		List<CustomTicCouAndCos> list = new ArrayList<CustomTicCouAndCos>();
		for(CustomTicCouAndCos cus : couponUsers){
			if (cus.getCou().getCouGoodsType()==0) {
				list.add(cus);
			} else if (cus.getCou().getCouGoodsType()==1){
				Integer[] category= cus.getCou().getCouGoodsValue();
				for(int i = 0;i <category.length; i++) {
					if(category[i]==catId){
						list.add(cus);
					}
				}
			}else{
				Integer[] category= cus.getCou().getCouGoodsValue();
				String[] gooId = gooIds.split(","); 
				cat:for (int j = 0;j < (gooId.length -1) ; j++) {
					for (int i = 0;i <category.length; i++) {
						if(String.valueOf(category[i]) == gooId[i]){
							list.add(cus);
							break cat;
						}
					}
				}
			}
		}
		return ResponseUtil.ok(list);
	}

	/**
	 * 优惠券领取
	 *
	 * @param userId 用户ID
	 * @param body   请求内容， { couponId: xxx }
	 * @return 操作结果
	 */
	@PostMapping("receive")
	public Object receive(@LoginUser Integer userId, @RequestBody String body) {
		System.out.println(body);
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer couponId = JacksonUtil.parseInteger(body, "couponId");
		if (couponId == null) {
			return ResponseUtil.badArgument();
		}
		// 查询优惠券
		TicCoupon coupon = couponService.findById(couponId);
		if (coupon == null) {
			return ResponseUtil.badArgumentValue();
		}

		// 当前已领取数量和总数量比较
		Integer total = coupon.getCouTotal();
		if (total == 0) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已领完");
		}

		// 当前用户已领取数量和用户限领数量比较
		Integer limit = coupon.getCouLimit().intValue();
		Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
		if ((limit != 0) && (userCounpons >= limit)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已经领取过");
		}

		// 优惠券分发类型
		// 例如注册赠券类型的优惠券不能领取
		Short type = coupon.getCouType();
		if (type.equals(CouponConstant.TYPE_REGISTER)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
		} else if (type.equals(CouponConstant.TYPE_CODE)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券只能兑换");
		} else if (!type.equals(CouponConstant.TYPE_COMMON)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
		}

		// 优惠券状态，已下架或者过期不能领取
		Short status = coupon.getCouStatus();
		if (status.equals(CouponConstant.STATUS_OUT)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已领完");
		} else if (status.equals(CouponConstant.STATUS_EXPIRED)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券已经过期");
		}
		//时间限制 (0:天数,1:具体时间)
		Short couTimeType = coupon.getCouTimeType();
		// 用户领券记录
		TicCouponUser couponUser = new TicCouponUser();
		if (couTimeType == 0){
			Short couDays = coupon.getCouDays();
			LocalDateTime now = LocalDateTime.now();
			couponUser.setCosEndTime(now.plusDays(couDays));
		} else {
			couponUser.setCosEndTime(coupon.getCouEndTime());
		}
		couponUser.setCosCouponId(couponId);
		couponUser.setCosUserId(userId);
		Short timeType = coupon.getCouTimeType();
		if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
			couponUser.setCosStartTime(coupon.getCouStartTime());
			couponUser.setCosEndTime(coupon.getCouEndTime());
		} else {
			LocalDateTime now = LocalDateTime.now();
			couponUser.setCosStartTime(now);
			couponUser.setCosEndTime(now.plusDays(coupon.getCouDays()));
		}
		couponUserService.add(couponUser);

		// 优惠劵领取后减少
		total = total - 1;
		Integer couGetCount = coupon.getCouGetCount() + 1;
		coupon.setCouTotal(total);
		coupon.setCouGetCount(couGetCount);
		couponService.updateById(coupon);
		return ResponseUtil.ok();
	}

	/**
	 * 优惠券兑换
	 *
	 * @param userId 用户ID
	 * @param body   请求内容， { code: xxx }
	 * @return 操作结果
	 */
	@PostMapping("exchange")
	public Object exchange(@LoginUser Integer userId, @RequestBody String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		String code = JacksonUtil.parseString(body, "code");
		if (code == null) {
			return ResponseUtil.badArgument();
		}

		TicCoupon coupon = couponService.findByCode(code);
		if (coupon == null) {
			return ResponseUtil.fail(WxResponseCode.COUPON_CODE_INVALID, "优惠券不正确");
		}
		Integer couponId = coupon.getCouId();

		// 当前已领取数量和总数量比较
		Integer total = coupon.getCouTotal();
		Integer totalCoupons = couponUserService.countCoupon(couponId);
		if ((total != 0) && (totalCoupons >= total)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		}

		// 当前用户已领取数量和用户限领数量比较
		Integer limit = coupon.getCouLimit().intValue();
		Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
		if ((limit != 0) && (userCounpons >= limit)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		}

		// 优惠券分发类型
		// 例如注册赠券类型的优惠券不能领取
		Short type = coupon.getCouType();
		if (type.equals(CouponConstant.TYPE_REGISTER)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
		} else if (type.equals(CouponConstant.TYPE_COMMON)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券只能领取，不能兑换");
		} else if (!type.equals(CouponConstant.TYPE_CODE)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
		}

		// 优惠券状态，已下架或者过期不能领取
		Short status = coupon.getCouStatus();
		if (status.equals(CouponConstant.STATUS_OUT)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		} else if (status.equals(CouponConstant.STATUS_EXPIRED)) {
			return ResponseUtil.fail(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券已经过期");
		}

		// 用户领券记录
		TicCouponUser couponUser = new TicCouponUser();
		couponUser.setCosCouponId(couponId);
		couponUser.setCosUserId(userId);
		Short timeType = coupon.getCouTimeType();
		if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
			couponUser.setCosStartTime(coupon.getCouStartTime());
			couponUser.setCosEndTime(coupon.getCouEndTime());
		} else {
			LocalDateTime now = LocalDateTime.now();
			couponUser.setCosStartTime(now);
			couponUser.setCosEndTime(now.plusDays(coupon.getCouDays()));
		}
		couponUserService.add(couponUser);

		return ResponseUtil.ok();
	}
}