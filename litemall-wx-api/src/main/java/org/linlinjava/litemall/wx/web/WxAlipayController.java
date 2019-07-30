package org.linlinjava.litemall.wx.web;


import org.linlinjava.litemall.wx.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;

@RestController
@RequestMapping("/wx/alipay")
@Validated
public class WxAlipayController {

	@Autowired
	private AliPayService alipay;

	/**
	 * 二维码付款
	 * @param orderId
	 * @return
	 * @throws AlipayApiException
	 */
	@GetMapping("qrCode")
	public Object qrCode(@RequestParam("orderId") Integer orderId) throws AlipayApiException {
		return alipay.qrCode(orderId);
	}



	/**
	 * 付款码付款
	 * @param authCode
	 * @return
	 * @throws AlipayApiException
	 */
	@GetMapping("authCode")
	public Object authCode(@RequestParam("authCode") String authCode,@RequestParam("orderId") Integer orderId) throws AlipayApiException {
		return alipay.authCode(authCode, orderId);
	}
	/**
	 * 退款
	 * @param orderId
	 * @return
	 */
	@GetMapping("aliRefund")
	public Object AliRefund(@RequestParam("orderId") Integer orderId)throws Exception{
		return alipay.AliRefund(orderId);
	}

}
