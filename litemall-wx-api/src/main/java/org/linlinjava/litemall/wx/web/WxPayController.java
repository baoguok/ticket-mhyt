package org.linlinjava.litemall.wx.web;

import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.WxOrderService;
import org.linlinjava.litemall.wx.service.WxRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/wx/wxpay")
@Validated
public class WxPayController {
	@Autowired
	private WxOrderService wxOrderService;
	@Autowired
	private WxRefundService refundService;

	@PostMapping("pay")
	public Object list(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
		return wxOrderService.prepay(userId,body,request);
	}
	
	@GetMapping("natpay")
	public Object Natpay(@RequestParam("orderId") Integer orderId, HttpServletRequest request) {
		return wxOrderService.Nativeprepay(orderId,request);
	}

	/**
	 * 付款码支付(微信)
	 * @param orderId
	 * @param authCode
	 * @param request
	 * @return
	 */
	@GetMapping("acpay")
	public  Object authCodepay(@RequestParam("orderId") Integer orderId, @RequestParam("authCode") String authCode, HttpServletRequest request) {
		System.out.println("微信支付:" + orderId + authCode);
		return  wxOrderService.authCodepay(orderId,authCode,request);
	}

	/**
	 * 退款
	 */
	@GetMapping("refund")
	public Object refund(@LoginUser Integer userId,Integer catId ,Integer orderId){
		System.out.println("退票：catId:"+catId + ",orderId" + orderId);
		if(StringUtils.isEmpty(userId)){
			return ResponseUtil.unlogin();
		}
		if(StringUtils.isEmpty(catId) || StringUtils.isEmpty(orderId)){
			return ResponseUtil.badArgument();
		}
		// 计算退票手续费
		Map<String, Object> map = refundService.costRefund(catId, orderId);
		Integer errno = (Integer) map.get("errno");
		if(errno == -1) {
			return ResponseUtil.noBack();
		}
		TicOrder costRes = (TicOrder) map.get("items");
		if(StringUtils.isEmpty(costRes)){
			return ResponseUtil.badArgument();
		}
		return wxOrderService.wxrefund(userId, costRes);
	}
}
