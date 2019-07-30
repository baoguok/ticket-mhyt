package org.linlinjava.litemall.admin.win;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;
import org.linlinjava.litemall.admin.dao.TicOrderReq;
import org.linlinjava.litemall.admin.service.WinOrderService;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.request.TicStaticRequest;
import org.linlinjava.litemall.db.response.TicOrderNumResponse;
import org.linlinjava.litemall.db.response.TicOrderResponse;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.linlinjava.litemall.db.service.TicStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/win/order")
@Validated
public class WinOrderController {
	@Autowired
	private TicOrderService orderService;
	@Autowired
	private WinOrderService winOrderService;
	@Autowired
	private TicCategoryService categoryService;
	@Autowired
	private TicStatisService statisService;

	public Object create() {
		return null;
	}

	/**
	 * 查询用户订单(可用于取票,订单状态：201：已付款)
	 *
	 * @param ordMobile  出游人手机号
	 * @param ordUcardId 出游人身份证
	 * @param ordColCode 取票码
	 * @return
	 */
	@GetMapping("list")
	public Object listOrg(String ordMobile, String ordUcardId, String ordColCode, String ordStatus) {
		if (StringUtils.isEmpty(ordMobile) && StringUtils.isEmpty(ordUcardId)
						&& StringUtils.isEmpty(ordColCode) && StringUtils.isEmpty(ordStatus)) {
			return ResponseUtil.badArgumentValue();
		}
		TicOrder ticOrder = new TicOrder();
		ticOrder.setOrdMobile(ordMobile);
		ticOrder.setOrdUcardId(ordUcardId);
		ticOrder.setOrdColCode(ordColCode);
		short aShort = (short) Integer.parseInt(ordStatus);
		ticOrder.setOrdOrderStatus(aShort);
		List<TicOrderResponse> orgList = orderService.orderGoogList(ticOrder);
		long total = PageInfo.of(orgList).getTotal();
		Map<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("items", orgList);
		return ResponseUtil.ok(map);
	}

	/**
	 * 修改用户订单信息--（可用于退票）
	 *
	 * @param orderReq (订单Id--ordId,订单状态--ordOrderStatus(203:已退款),num:退票数量,selId:售票员Id,bool:售卖方式：true：售票，false:退票)
	 * @return
	 */
	@PostMapping("update")
	public Object update(@RequestBody TicOrderReq orderReq) {
		Integer ordId = orderReq.getOrdId();
		Short ordStatus = orderReq.getOrdOrderStatus();
		int i;
		if (StringUtils.isEmpty(ordId) && StringUtils.isEmpty(ordStatus)) {
			return ResponseUtil.badArgument();
		}
		// 修改订单状态
		int update = orderService.update(orderReq);
		if (update == 0) {
			return ResponseUtil.badArgument();
		}
		// 更新售票信息
		TicStaticRequest staticRequest = new TicStaticRequest();
		boolean payWay = orderReq.isBool();
		staticRequest.setNum(orderReq.getNum());
		staticRequest.setStaSelId(orderReq.getSelId());
		staticRequest.setBool(payWay);
		if (payWay) {
			i = statisService.updateSold(staticRequest);
		} else {
			i = statisService.updateRetired(staticRequest);
		}
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	/**
	 * 创建订单 --（售票）
	 *
	 * @param body { ordCatId: xxx, adminId: xxx, TotalPrice:xxx ,couDiscount: xxx, ordPayWay:xxx ,params: [TicGoods]}
	 * @return     门票类别Id       售票员Id      订单金额         优惠金额(可不传,或为0)  售卖方式(1现金,2微信,3支付宝)  门票集合
	 */
	@PostMapping("add")
	public Object create(@RequestBody String body) {
		Integer catId = Integer.valueOf(JacksonUtil.parseString(body, "ordCatId"));
		// 查询门票类别
		TicCategory ticCategory = categoryService.query(catId);
		Integer catPeopleNum = ticCategory.getCatPeopleNum();
		if (StringUtils.isEmpty(catPeopleNum)) {
			catPeopleNum = 1;
		}
		//创建订单
		Map<String, Integer> objectMap = winOrderService.create(body,catPeopleNum);
		Integer errno = objectMap.get("errno");
		if (errno == -1) {
			return ResponseUtil.badArgument();
		}
		//门票数量
		Integer num = objectMap.get("num");
		//订单Id
		Integer orderId = objectMap.get("orderId");
		//更新售票信息
		Integer selId = Integer.valueOf(JacksonUtil.parseString(body, "adminId"));
		TicStaticRequest staticRequest = new TicStaticRequest();
		staticRequest.setNum(num);
		staticRequest.setStaSelId(selId);
		staticRequest.setBool(true);
		int i = statisService.updateSold(staticRequest);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		//查询订单信息
		TicOrder ticOrder = new TicOrder();
		ticOrder.setOrdId(orderId);
		List<TicOrderResponse> orgList = orderService.orderGoogList(ticOrder);
		long total = PageInfo.of(orgList).getTotal();
		Map<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("items", orgList);
		return ResponseUtil.ok(map);
	}

	/**
	 * 取票后，修改订单状态
	 *
	 * @param orderReq (订单Id--ordId,订单状态--ordOrderStatus(301:已使用))
	 * @return
	 */
	@PostMapping("updateStatus")
	public Object updateStatus(@RequestBody TicOrderReq orderReq) {
		Integer ordId = orderReq.getOrdId();
		Short ordStatus = orderReq.getOrdOrderStatus();
		if (StringUtils.isEmpty(ordId) && StringUtils.isEmpty(ordStatus)) {
			return ResponseUtil.badArgument();
		}
		// 修改订单状态
		int update = orderService.update(orderReq);
		if (update == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	/**
	 *
	 */
	/**
	 * 查询当日售票信息
	 * @param ordAdminId  售票Id
	 * @param ordPayWay 购买方式
	 * @return
	 */
	@GetMapping("num")
	public Object listNum(Integer page, Integer limit, Integer ordPayWay, Integer ordAdminId){
		List<TicOrderNumResponse> ticOrders = orderService.ticNumber(page, limit, ordPayWay, ordAdminId);
		long total = PageInfo.of(ticOrders).getTotal();
		Map<Object, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("items", ticOrders);
		return ResponseUtil.ok(map);
	}

	/**
	 * 查询所有售票信息
	 * @param ordAdminId  售票Id
	 * @param ordOrderStatus 订单状态 301 已使用
	 * @return
	 */
	@GetMapping("numAll")
	public Object listNumAll(Integer ordAdminId, Integer ordOrderStatus,Integer ordPayWay ,Integer day){
		System.out.println("ordAdminId:   "+ordAdminId+"   ordOrderStatus:   "+ordOrderStatus + "   ordpayway:   "+ordPayWay+ "   day:   " +day);
		List<TicOrderNumResponse> ticOrders = orderService.ticNumberAll(ordAdminId,ordOrderStatus,ordPayWay,day);
		Map<Object, Object> map = new HashMap<>();
		map.put("total", ticOrders.size());
		map.put("items", ticOrders);
		return ResponseUtil.ok(map);
	}

}
