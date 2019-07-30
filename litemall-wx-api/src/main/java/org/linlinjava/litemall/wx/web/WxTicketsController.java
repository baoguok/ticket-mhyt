package org.linlinjava.litemall.wx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.NotifyType;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.response.TicOrderResponse;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

/**
 * 自助取票机 jsonp 跨域请求
 * 
 * @author 谭糠
 *
 */
@RestController
@RequestMapping("/tickets")
public class WxTicketsController {
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private TicCategoryService categoryService;
	@Autowired
	private TicOrderService orderService;
	@Autowired
	private TicGoodsService goodsService;

	@GetMapping("shList")
	public void shList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<TicCategory> list = categoryService.SelfHelpList(page, limit);
		long total = PageInfo.of(list).getTotal();
		JSONArray jsonArray = new JSONArray(list);
		out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"total\":" + total + ",\"data\":"
				+ jsonArray.toString() + "})");
	}

	@GetMapping("getticke")
	public void Getticke(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		TicOrderResponse order = orderService.details(31);
		if (order == null) {
			out.print(callback + "({\"errno\":404,\"errmsg\":\"未查询到门票,请核对信息\"})");
		} else {
			out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"data\":" + JSON.toJSONString(order) + "})");
		}
	}

	@GetMapping("goodStock")
	public Object GoodStock(@RequestParam("Time") String Time, @RequestParam("catId") Integer catId) {
		return goodsService.GetStock(Time, catId);
	}

	/**
	 * 身份证
	 * 
	 * @return
	 */
	@GetMapping("reqidCard")
	public void reqidCard(@RequestParam("idCard") String idCard, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ucardid", idCard);
		List<TicOrderResponse> list = orderService.UserDetails(map);
		JSONArray jsonArray = new JSONArray(list);
		if (list.size() == 0) {
			out.print(callback + "({\"errno\":404,\"errmsg\":\"未查询到门票,请核对信息\"})");
		} else {
			out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"data\":" + jsonArray.toString() + "})");
		}
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	@GetMapping("reqphone")
	public void Phone(@RequestParam("phone") String phone, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", phone);
		List<TicOrderResponse> list = orderService.UserDetails(map);
		if (list.size() < 1) {
			out.print(callback + "({\"errno\":404,\"errmsg\":\"未查询到门票,请核对信息\"})");
		} else {
			int random = (int) ((Math.random() * 9 + 1) * 100000);
			redisTemplate.opsForValue().set(phone, random + "", 60 * 3, TimeUnit.SECONDS);
			NotifyType notifyType = null;
			// 腾讯短信平台
			String[] Tenparams = new String[] { random + "" };
			notifyService.notifySmsTemplate(phone, notifyType.CAPTCHA, Tenparams);
			out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\"})");
		}
	}

	/**
	 * 验证码
	 * 
	 * @return
	 */
	@GetMapping("reqchecking")
	public void checking(@RequestParam("phone") String phone, @RequestParam("code") String code,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", phone);
		List<TicOrderResponse> list = orderService.UserDetails(map);
		if (redisTemplate.hasKey(phone)) {
			JSONArray jsonArray = new JSONArray(list);
			if (code.equals(redisTemplate.opsForValue().get(phone))) {
				out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"data\":" + jsonArray.toString() + "})");
			} else {
				out.print(callback + "({\"errno\":404,\"errmsg\":\"验证码错误\"})");
			}
		} else {
			out.print(callback + "({\"errno\":405,\"errmsg\":\"验证码已过期\"})");
		}
	}

	/**
	 * 二维码
	 * 
	 * @return
	 */
	@GetMapping("reqqrCode")
	public void QrCode(@RequestParam("qrCode") String qrCode, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colcode", qrCode);
		List<TicOrderResponse> list = orderService.UserDetails(map);
		if (list.size() == 0) {
			out.print(callback + "({\"errno\":404,\"errmsg\":\"未查询到门票,请核对信息\"})");
		} else {
			JSONArray jsonArray = new JSONArray(list);
			out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"data\":" + jsonArray.toString() + "})");
		}
	}

	/**
	 * 取票码
	 * 
	 * @return
	 */
	@GetMapping("reqtakeCode")
	public void TakingCode(@RequestParam("takeCode") String takeCode, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colcode", takeCode);
		List<TicOrderResponse> list = orderService.UserDetails(map);
		if (list.size() == 0) {
			out.print(callback + "({\"errno\":404,\"errmsg\":\"未查询到门票,请核对信息\"})");
		} else {
			JSONArray jsonArray = new JSONArray(list);
			out.print(callback + "({\"errno\":200,\"errmsg\":\"成功\",\"data\":" + jsonArray.toString() + "})");
		}
	}

}
