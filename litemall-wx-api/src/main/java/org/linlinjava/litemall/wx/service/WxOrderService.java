package org.linlinjava.litemall.wx.service;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.WriterException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.config.WxProperties;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.NotifyType;
import org.linlinjava.litemall.core.qcode.QCodeService;
import org.linlinjava.litemall.core.util.QrCodeCreateUtil;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.db.util.OrderHandleOption;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.linlinjava.litemall.wx.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;
import static org.linlinjava.litemall.wx.util.AdminResponseCode.ORDER_REFUND_FAILED;
import static org.linlinjava.litemall.wx.util.WxResponseCode.*;

/**
 * 订单服务
 *
 * <p>
 * 订单状态： 101 订单生成，未支付；102，下单后未支付用户取消；103，下单后未支付超时系统自动取消 201
 * 支付完成，商家未发货；202，订单生产，已付款未发货，但是退款取消； 301 商家发货，用户未确认； 401 用户确认收货； 402
 * 用户没有确认收货超过一定时间，系统自动确认收货；
 *
 * <p>
 * 用户操作： 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
 * 当301商家已发货时，此时用户可以有确认收货的操作 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，或者再次购买
 * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，或者再次购买
 *
 * <p>
 * 注意：目前不支持订单退货和售后服务
 */
@Service
public class WxOrderService {
	private final Log logger = LogFactory.getLog(WxOrderService.class);

	@Autowired
	private TicUserService userService;
	@Autowired
	private TicOrderService orderService;
	@Autowired
	private LitemallOrderGoodsService orderGoodsService;
	@Autowired
	private TicCategoryService cateService;
	// @Autowired
	// private LitemallRegionService regionService;
	@Autowired
	private LitemallGoodsProductService productService;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private TicUserFormIdService formIdService;
	@Autowired
	private LitemallGrouponRulesService grouponRulesService;
	@Autowired
	private LitemallGrouponService grouponService;
	@Autowired
	private QCodeService qCodeService;
	@Autowired
	private TicCommentService commentService;
	@Autowired
	private QrCodeCreateUtil qrCodeCreateUtil;
	@Autowired
	private TicOrderGoodService orgService;
	@Autowired
	private NotifyService notifyService;

	@SuppressWarnings("unused")
	private String detailedAddress(LitemallAddress litemallAddress) {
		String provinceName = litemallAddress.getProvinceName();
		String cityName = litemallAddress.getCityName();
		String areaName = litemallAddress.getAreaName();
		String detailInfo = litemallAddress.getDetailInfo();
		String postalCode = litemallAddress.getPostalCode();
		String fullRegion = provinceName + " " + cityName + " " + areaName + " " + detailInfo + " " + postalCode;
		return fullRegion;
	}

	/**
	 * 订单列表
	 *
	 * @param userId   用户ID
	 * @param showType 订单信息： 0，全部订单； 1，待付款； 2，已付款； 3，已使用； 4，已取消；5，待评价。
	 * @param page     分页页数
	 * @param size     分页大小
	 * @return 订单列表
	 */
	public Object list(Integer userId, Integer showType, Integer page, Integer size) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		List<Short> orderStatus = OrderUtil.orderStatus(showType);
		List<TicOrder> orderList = orderService.queryByOrderStatus(userId, orderStatus);
		int count = orderService.countByOrderStatus(userId, orderStatus);

		List<Map<String, Object>> orderVoList = new ArrayList<>(orderList.size());
		for (TicOrder order : orderList) {
			Map<String, Object> orderVo = new HashMap<>();
			orderVo.put("id", order.getOrdId());
			orderVo.put("orderSn", order.getOrdOrderSn());
			orderVo.put("actualPrice", order.getOrdActualPrice());
			orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
			orderVo.put("handleOption", OrderUtil.build(order));

			LitemallGroupon groupon = grouponService.queryByOrderId(order.getOrdId());
			if (groupon != null) {
				orderVo.put("isGroupin", true);
			} else {
				orderVo.put("isGroupin", false);
			}
			// 查询订单门票信息
			List<TicOrderGoods> orderGoodsList = orderGoodsService.queryByOid(order.getOrdId());
			List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
			for (TicOrderGoods orderGoods : orderGoodsList) {
				Map<String, Object> orderGoodsVo = new HashMap<>();
				orderGoodsVo.put("id", orderGoods.getOrgId());
				orderGoodsVo.put("goodsName", orderGoods.getOrgGoodsName());
				orderGoodsVo.put("number", orderGoods.getOrgNumber());
				orderGoodsVoList.add(orderGoodsVo);
			}
			orderVo.put("goodsList", orderGoodsVoList);

			orderVoList.add(orderVo);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("count", count);
		result.put("data", orderVoList);

		return ResponseUtil.ok(result);
	}

	/**
	 * 订单详情
	 *
	 * @param userId  用户ID
	 * @param orderId 订单ID
	 * @return 订单详情
	 */
	public Object detail(Integer userId, Integer orderId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		// 订单信息
		TicOrder order = orderService.findById(orderId);
		if (null == order) {
			return ResponseUtil.fail(ORDER_UNKNOWN, "订单不存在");
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.fail(ORDER_INVALID, "不是当前用户的订单");
		}
		Map<String, Object> orderVo = new HashMap<String, Object>();
		orderVo.put("id", order.getOrdId());
		orderVo.put("orderSn", order.getOrdOrderSn());
		orderVo.put("addTime", order.getOrdAddTime());
		orderVo.put("mobile", order.getOrdMobile());
		orderVo.put("goodsPrice", order.getOrdGoodsPrice());
		orderVo.put("actualPrice", order.getOrdActualPrice());
		orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
		orderVo.put("handleOption", OrderUtil.build(order));

		List<TicOrderGoods> orderGoodsList = orderGoodsService.queryByOid(order.getOrdId());

		Map<String, Object> result = new HashMap<>();
		result.put("orderInfo", orderVo);
		result.put("orderGoods", orderGoodsList);

		return ResponseUtil.ok(result);

	}

	/**
	 * 小程序提交订单
	 * <p>
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ cosId：xxx, timedate: xxx, TotalPrice: xxx, ordCatId: xxx, couDiscount:
	 *               xxx, Idcard: xxx, username: xxx, phone: xxx, viewId: xxx, params:[TicGoods]}
	 * @return 提交订单操作结果
	 */
	@Transactional
	public Object submit(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		if (body == null) {
			return ResponseUtil.badArgument();
		}
		TicOrder order = new TicOrder();
		// 获取所购买的所有门票及数量
		Integer start = body.indexOf("[");
		Integer end = body.lastIndexOf("]");
		String StrGoods = body.substring(start, end + 1);
		Gson gson = new Gson();
		// 获取门票信息集合
		List<TicGoods> list = gson.fromJson(StrGoods, new TypeToken<List<TicGoods>>() {
		}.getType());// 获取所购买的所有门票及数量
		Integer num = 0; //门票数量
		for (TicGoods goods : list) {
			if (goods.getNum() > 0) {
				num = num + goods.getNum();
			}
		}
		// 创建订单信息
		order.setOrdUserId(userId); // 用户ID
		order.setOrdOrderSn(getUUID()); // 订单编号
		order.setOrdOrderStatus(Short.parseShort("101")); // 订单状态
		order.setOrdTourist(JacksonUtil.parseString(body, "username")); // 出游人姓名
		order.setOrdMobile(JacksonUtil.parseString(body, "phone")); // 出游人电话
		order.setOrdUcardId(JacksonUtil.parseString(body, "Idcard")); // 出游人身份证
		BigDecimal TotalPrice = new BigDecimal(JacksonUtil.parseString(body, "TotalPrice"));
		order.setOrdGoodsPrice(TotalPrice); // 订单总价
		order.setOrdCouponId(JacksonUtil.parseInteger(body, "cosId")); // 优惠券ID
		BigDecimal couDiscount = new BigDecimal(JacksonUtil.parseString(body, "couDiscount"));
		order.setOrdCouponPrice(couDiscount); // 优惠券金额
		order.setOrdOrderPrice(TotalPrice); // 订单总价
		order.setOrdActualPrice(TotalPrice.subtract(couDiscount)); // 实付金额
		// 总价-优惠金额
		order.setOrdPayId(getUUID()); // 微信支付编号
		order.setOrdPayTime(LocalDateTime.now()); // 微信支付时间
		order.setOrdPayWay(JacksonUtil.parseInteger(body, "ordPayWay")); // 购买方式
		order.setOrdAdminId(0); // 售票员ID
		String confirmTimeStr = JacksonUtil.parseString(body, "timedate");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		order.setOrdConfirmTime(LocalDateTime.parse(confirmTimeStr, df)); // 使用时间
		order.setOrdColCode(getUUID()); // 取票码
		order.setOrdParkCode(getElevenUUId()); // 入园码
		order.setOrdGoodsNum(num);// 门票数量
		Integer ordCatId = Integer.valueOf(JacksonUtil.parseString(body, "ordCatId"));
		order.setOrdCatId(ordCatId);// 门票类别Id
		order.setOrdComment(0);// 是否可评价
		order.setOrdPayTime(LocalDateTime.now());// 支付时间
		// 查询门票类别
		TicCategory query = cateService.query(ordCatId);
		Integer[] catViewId = query.getCatViewId();
		// 添加大门闸机ID
		List<Integer> viewInteger = Arrays.asList(catViewId);
		List<Integer> viewList = new ArrayList(viewInteger);
		viewList.add(0);
		Integer[] inte = new Integer[viewList.size()];
		catViewId = viewList.toArray(inte);
		order.setOrdQrCode(qrCodeCreateUtil.createQR(300, "jpg", "storage\\" + order.getOrdColCode() + ".jpg", order.getOrdColCode()));
		order.setOrdAddTime(LocalDateTime.now());
		order.setOrdUpdateTime(LocalDateTime.now());
		order.setOrdDeleted(false);
		int orderAdd = orderService.create(order);
		if (orderAdd == 0) {
			return ResponseUtil.badArgument();
		}
		// 添加订单门票关联信息
		List<TicOrderGoods> orgList = new ArrayList<>();
		for (TicGoods goods : list) {
			if (goods.getNum() > 0) {
				for (int i = 0; i < goods.getNum(); i++) {
					TicOrderGoods orderGoods = new TicOrderGoods();
					orderGoods.setOrgOrderId(order.getOrdId());
					orderGoods.setOrgGoodsId(goods.getGooId());
					orderGoods.setOrgGoodsName(goods.getGooName());
					orderGoods.setOrgNumber((short) 1);
					orderGoods.setOrgViewId(catViewId);
					orderGoods.setOrgAddTime(LocalDateTime.now());
					orderGoods.setOrgUpdateTime(LocalDateTime.now());
					orderGoods.setOrgPrice(goods.getGooRetailPrice());// 门票价格
					orderGoods.setOrgColCode(getTenUUID());// 入园码
					orderGoods.setOrgDeleted(false);
					orgList.add(orderGoods);
				}
			}
		}
		int batch = orgService.createBatch(orgList);
		if (batch == 0) {
			return ResponseUtil.badArgument();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", order.getOrdId());
		return ResponseUtil.ok(map);
	}

	/**
	 * 自助取票机提交订单
	 * <p>
	 *
	 * @param body 订单信息，{ TotalPrice: xxx, params: [TicGoods]}
	 * @return 提交订单操作结果
	 */
	@Transactional
	public Object SelfHelpsubmit(String body) {
		if (body == null) {
			return ResponseUtil.badArgument();
		}
		TicOrder order = new TicOrder();
		// 获取所购买的所有门票及数量
		Integer start = body.indexOf("[");
		Integer end = body.lastIndexOf("]");
		String StrGoods = body.substring(start, end + 1);
		Gson gson = new Gson();
		// 获取门票信息集合
		List<TicGoods> list = gson.fromJson(StrGoods, new TypeToken<List<TicGoods>>() {
		}.getType());// 获取所购买的所有门票及数量
		Integer num = 0; //门票数量
		for (TicGoods goods : list) {
			if (goods.getNum() > 0) {
				num = num + goods.getNum();
			}
		}
		// 创建订单信息
		order.setOrdUserId(-1); // 用户ID
		order.setOrdOrderSn(getUUID()); // 订单编号
		order.setOrdOrderStatus(Short.parseShort("101")); // 订单状态
		order.setOrdTourist("SelfHelp"); // 出游人姓名
		order.setOrdMobile("SelfHelp"); // 出游人电话
		order.setOrdUcardId("SelfHelp"); // 出游人身份证
		BigDecimal TotalPrice = new BigDecimal(JacksonUtil.parseString(body, "TotalPrice"));
		order.setOrdGoodsPrice(TotalPrice); // 订单总价
		order.setOrdCouponId(-1); // 优惠券ID
		order.setOrdCouponPrice(new BigDecimal(0)); // 优惠券金额
		order.setOrdOrderPrice(TotalPrice); // 订单总价
		order.setOrdActualPrice(TotalPrice); // 实付金额
		order.setOrdPayId(getUUID()); // 微信支付编号
		order.setOrdPayTime(LocalDateTime.now()); // 微信支付时间
		order.setOrdPayWay(JacksonUtil.parseInteger(body, "ordPayWay")); // 购买方式
		order.setOrdAdminId(0); // 售票员ID
		order.setOrdConfirmTime(LocalDateTime.now()); // 使用时间
		order.setOrdColCode(getElevenUUId()); // 取票码
		order.setOrdGoodsNum(num);// 门票数量
		Integer ordCatId = Integer.valueOf(JacksonUtil.parseString(body, "ordCatId"));
		order.setOrdCatId(ordCatId);// 门票类别Id
		order.setOrdComment(0);// 是否可评价
		order.setOrdPayTime(LocalDateTime.now());// 支付时间
		order.setOrdQrCode(qrCodeCreateUtil.createQR(300, "jpg", "storage\\" + order.getOrdColCode() + ".jpg", order.getOrdColCode()));
		order.setOrdDeleted(false);
		int orderAdd = orderService.create(order);
		if (orderAdd == 0) {
			return ResponseUtil.badArgument();
		}
		// 添加订单门票关联信息
		List<TicOrderGoods> orgList = new ArrayList<>();
		for (TicGoods goods : list) {
			System.out.println(goods.getNum());
			goods.setNum((short) 2);
			if (goods.getNum() > 0) {
				for (int i = 0; i < goods.getNum(); i++) {
					TicOrderGoods orderGoods = new TicOrderGoods();
					orderGoods.setOrgOrderId(order.getOrdId());
					orderGoods.setOrgGoodsId(goods.getGooId());
					orderGoods.setOrgGoodsName(goods.getGooName());
					orderGoods.setOrgNumber((short) 1);
					orderGoods.setOrgAddTime(LocalDateTime.now());
					orderGoods.setOrgUpdateTime(LocalDateTime.now());
					orderGoods.setOrgPrice(goods.getGooRetailPrice());// 门票价格
					orderGoods.setOrgColCode(getTenUUID());// 取票码
					orderGoods.setOrgDeleted(false);
					orgList.add(orderGoods);
				}
			}
		}
		int batch = orgService.createBatch(orgList);
		if (batch == 0) {
			return ResponseUtil.badArgument();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", order.getOrdId());
		return ResponseUtil.ok(map);
	}

	public String getUUID() {
		// 随机生成一位整数
		int random = (int) (Math.random() * 9 + 1);
		String valueOf = String.valueOf(random);
		// 生成uuid的hashCode值
		int hashCode = UUID.randomUUID().toString().hashCode();
		// 可能为负数
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		return valueOf + String.format("%015d", hashCode);
	}

	// 生成15位唯一性的订单号
	public String getTenUUID() {
		// 获取日期
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyMMdd");
		String str = format.format(date);
		// 随机生成一位小写字母
		char c = (char) (int) (Math.random() * 26 + 97);
		String valueOf = String.valueOf(c);
		// 生成uuid的hashCode值
		int hashCode = UUID.randomUUID().toString().hashCode();
		// 可能为负数
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		String s = String.valueOf(hashCode).substring(0, 3);
		return str + s + valueOf;
	}

	/**
	 * 生成11位
	 *
	 * @return
	 */
	public String getElevenUUId() {
		// 获取日期
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyMMdd");
		String str = format.format(date);
		// 随机生成一位小写字母
		char c = (char) (int) (Math.random() * 26 + 97);
		String valueOf = String.valueOf(c);
		// 生成uuid的hashCode值
		int hashCode = UUID.randomUUID().toString().hashCode();
		// 可能为负数
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		String s = String.valueOf(hashCode).substring(0, 4);
		return str + s + valueOf;
	}

	/**
	 * 微信退款
	 *
	 * @param userId
	 * @return
	 */
	@Transactional
	public Object wxrefund(Integer userId, TicOrder order) {
		// 校验
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}
		// 微信支付订单Id
		String ordOrderSn = order.getOrdOrderSn();
		if (StringUtils.isEmpty(ordOrderSn)) {
			return ResponseUtil.badArgumentValue();
		}
		// 订单金额
		BigDecimal ordActualPrice = order.getOrdActualPrice();
		if (StringUtils.isEmpty(ordActualPrice)) {
			return ResponseUtil.badArgument();
		}
		// 元转分
		int fee = ordActualPrice.multiply(new BigDecimal(100)).intValue();
		// 微信退款
		WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
		wxPayRefundRequest.setOutTradeNo(ordOrderSn);
		wxPayRefundRequest.setOutRefundNo("refund_" + ordOrderSn);
		wxPayRefundRequest.setTotalFee(fee);
		wxPayRefundRequest.setRefundFee(fee);
		WxPayRefundResult wxPayRefundResult = null;
		try {
			wxPayRefundResult = wxPayService.refund(wxPayRefundRequest);
		} catch (WxPayException e) {
			e.printStackTrace();
			return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
		}
		if (!wxPayRefundResult.getReturnCode().equals("SUCCESS")) {
			logger.warn("refund fail: " + wxPayRefundResult.getReturnMsg());
			return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
		}
		// TODO 发送邮件和短信通知，这里采用异步发送
		// 退款成功通知用户, 例如“您申请的订单退款 [ 单号:{1} ] 已成功，请耐心等待到账。”
		// 注意订单号只发后6位
		notifyService.notifySmsTemplate(order.getOrdMobile(), NotifyType.REFUND,
				new String[]{ordOrderSn.substring(8, 14)});

		return ResponseUtil.ok();
	}

	/**
	 * 取消订单
	 * <p>
	 * 1. 检测当前订单是否能够取消； 2. 设置订单取消状态； 3. 商品货品库存恢复； 4. TODO 优惠券； 5. TODO 团购活动。
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 取消订单操作结果
	 */
	@Transactional
	public Object cancel(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer orderId = JacksonUtil.parseInteger(body, "orderId");
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}

		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		LocalDateTime preUpdateTime = order.getOrdUpdateTime();

		// 检测是否能够取消
		OrderHandleOption handleOption = OrderUtil.build(order);
		if (!handleOption.isCancel()) {
			return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
		}

		// 设置订单已取消状态
		order.setOrdOrderStatus(OrderUtil.STATUS_CANCEL);
		order.setOrdEndTime(LocalDateTime.now());
		if (orderService.updateWithOptimisticLocker(order) == 0) {
			throw new RuntimeException("更新数据已失效");
		}

		// 商品货品数量增加
		List<TicOrderGoods> orderGoodsList = orderGoodsService.queryByOid(orderId);
		for (TicOrderGoods orderGoods : orderGoodsList) {
			Integer productId = orderGoods.getOrgGoodsId();
			Short number = orderGoods.getOrgNumber();
			if (productService.addStock(productId, number) == 0) {
				throw new RuntimeException("商品货品库存增加失败");
			}
		}

		return ResponseUtil.ok();
	}

	/**
	 * 付款订单的预支付会话标识
	 * <p>
	 * 1. 检测当前订单是否能够付款 2. 微信商户平台返回支付订单ID 3. 设置订单付款状态
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 支付订单ID
	 */
	@Transactional
	public Object prepay(Integer userId, String body, HttpServletRequest request) {
		Date date = LocalToDate(LocalDateTime.now().plusDays(7));
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer orderId = JacksonUtil.parseInteger(body, "orderId");
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}
		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}
		// 检测是否能够取消
		OrderHandleOption handleOption = OrderUtil.build(order);
		if (!handleOption.isPay()) {
			return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能支付");
		}
		TicUser user = userService.findById(userId);
		String openid = user.getUseWeixinOpenid();
		if (openid == null) {
			return ResponseUtil.fail(AUTH_OPENID_UNACCESS, "订单不能支付");
		}
		WxPayMpOrderResult result = null;
		try {
			WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
			orderRequest.setOutTradeNo(order.getOrdOrderSn());
			orderRequest.setOpenid(openid);
			// orderRequest.setBody("订单：" + order.getOrderSn());
			orderRequest.setBody("桂阳望湖生态园");
			// 元转成分
			int fee = 0;
			BigDecimal actualPrice = order.getOrdActualPrice();
			fee = actualPrice.multiply(new BigDecimal(100)).intValue();
			orderRequest.setTotalFee(fee);
			orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
			result = wxPayService.createOrder(orderRequest);

			// 缓存prepayID用于后续模版通知
			String prepayId = result.getPackageValue();
			prepayId = prepayId.replace("prepay_id=", "");
			TicUserFormid userFormid = new TicUserFormid();
			userFormid.setForOpenid(user.getUseWeixinOpenid());
			userFormid.setForFormid(prepayId);
			userFormid.setForIsprepay(true);
			userFormid.setForUseamount(3);
			userFormid.setForExpireTime(date);
			formIdService.addUserFormid(userFormid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.fail(ORDER_PAY_FAIL, "订单不能支付");
		}
		return ResponseUtil.ok(result);
	}

	/**
	 * NATIVE 付款订单的预支付会话标识
	 * <p>
	 * 1. 检测当前订单是否能够付款 2. 微信商户平台返回支付订单ID 3. 设置订单付款状态
	 *
	 * @return 支付订单ID
	 */
	@SuppressWarnings("null")
	@Transactional
	public Object Nativeprepay(Integer orderId, HttpServletRequest request) {
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}
		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		WxPayNativeOrderResult result = null;
		try {
			WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
			WxProperties properties = new WxProperties();
			orderRequest.setOutTradeNo(order.getOrdOrderSn());
			orderRequest.setBody("桂阳望湖生态园");
			orderRequest.setTradeType("NATIVE");
			orderRequest.setNotifyUrl(properties.getNotifyUrl());
			// 元转成分
			int fee = 0;
			BigDecimal actualPrice = order.getOrdActualPrice();
			fee = actualPrice.multiply(new BigDecimal(100)).intValue();
			orderRequest.setTotalFee(fee);
			orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
			orderRequest.setProductId(order.getOrdPayId());
			result = wxPayService.createOrder(orderRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.fail(ORDER_PAY_FAIL, "订单不能支付");
		}

		// if (orderService.updateWithOptimisticLocker(order) == 0) {
		// return ResponseUtil.updatedDateExpired();
		// }
		return ResponseUtil.ok(result);
	}

	/**
	 * 付款码支付订单的预支付会话标识
	 * <p>
	 * 1. 检测当前订单是否能够付款 2. 微信商户平台返回支付订单ID 3. 设置订单付款状态
	 *
	 * @return 支付订单ID
	 */
	@SuppressWarnings("null")
	@Transactional
	public Object authCodepay(Integer orderId, String authCode, HttpServletRequest request) {
		if (authCode == null) {
			return ResponseUtil.badArgument();
		}
		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		WxPayMicropayResult result = null;
		try {
			WxPayMicropayRequest orderRequest = new WxPayMicropayRequest();
			WxProperties properties = new WxProperties();
			orderRequest.setOutTradeNo(order.getOrdOrderSn());
			orderRequest.setBody("桂阳望湖生态园");
			// 元转成分
			int fee = 0;
			BigDecimal actualPrice = order.getOrdActualPrice();
			fee = actualPrice.multiply(new BigDecimal(100)).intValue();
			orderRequest.setTotalFee(fee);
			orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
			orderRequest.setAuthCode(authCode);
			result = wxPayService.micropay(orderRequest);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return ResponseUtil.fail(ORDER_PAY_FAIL, "订单不能支付");
		}

		// if (orderService.updateWithOptimisticLocker(order) == 0) {
		// return ResponseUtil.updatedDateExpired();
		// }
		return ResponseUtil.ok(result);
	}

	/**
	 * 微信付款成功或失败回调接口
	 * <p>
	 * 1. 检测当前订单是否是付款状态; 2. 设置订单付款成功状态相关信息; 3. 响应微信商户平台.
	 *
	 * @param request  请求内容
	 * @param response 响应内容
	 * @return 操作结果
	 */
	@Transactional
	public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
		String xmlResult = null;
		try {
			xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		} catch (IOException e) {
			e.printStackTrace();
			return WxPayNotifyResponse.fail(e.getMessage());
		}

		WxPayOrderNotifyResult result = null;
		try {
			result = wxPayService.parseOrderNotifyResult(xmlResult);
		} catch (WxPayException e) {
			e.printStackTrace();
			return WxPayNotifyResponse.fail(e.getMessage());
		}

		logger.info("处理腾讯支付平台的订单支付");
		logger.info(result);

		String orderSn = result.getOutTradeNo();
		String payId = result.getTransactionId();

		// 分转化成元
		String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
		TicOrder order = orderService.findBySn(orderSn);
		if (order == null) {
			return WxPayNotifyResponse.fail("订单不存在 sn=" + orderSn);
		}

		// 检查这个订单是否已经处理过
		if (OrderUtil.isPayStatus(order) && order.getOrdPayId() != null) {
			return WxPayNotifyResponse.success("订单已经处理成功!");
		}

		// 检查支付订单金额
		if (!totalFee.equals(order.getOrdActualPrice().toString())) {
			return WxPayNotifyResponse.fail(order.getOrdOrderSn() + " : 支付金额不符合 totalFee=" + totalFee);
		}

		order.setOrdPayId(payId);
		order.setOrdPayTime(LocalDateTime.now());
		order.setOrdOrderStatus(OrderUtil.STATUS_PAY);
		if (orderService.updateWithOptimisticLocker(order) == 0) {
			// 这里可能存在这样一个问题，用户支付和系统自动取消订单发生在同时
			// 如果数据库首先因为系统自动取消订单而更新了订单状态；
			// 此时用户支付完成回调这里也要更新数据库，而由于乐观锁机制这里的更新会失败
			// 因此，这里会重新读取数据库检查状态是否是订单自动取消，如果是则更新成支付状态。
			order = orderService.findBySn(orderSn);
			int updated = 0;
			if (OrderUtil.isAutoCancelStatus(order)) {
				order.setOrdPayId(payId);
				order.setOrdPayTime(LocalDateTime.now());
				order.setOrdOrderStatus(OrderUtil.STATUS_PAY);
				updated = orderService.updateWithOptimisticLocker(order);
			}

			// 如果updated是0，那么数据库更新失败
			if (updated == 0) {
				return WxPayNotifyResponse.fail("更新数据已失效");
			}
		}

		// 支付成功，有团购信息，更新团购信息
		LitemallGroupon groupon = grouponService.queryByOrderId(order.getOrdId());
		if (groupon != null) {
			LitemallGrouponRules grouponRules = grouponRulesService.queryById(groupon.getRulesId());

			// 仅当发起者才创建分享图片
			if (groupon.getGrouponId() == 0) {
				String url = qCodeService.createGrouponShareImage(grouponRules.getGoodsName(), grouponRules.getPicUrl(),
						groupon);
				groupon.setShareUrl(url);
			}
			groupon.setPayed(true);
			if (grouponService.updateById(groupon) == 0) {
				return WxPayNotifyResponse.fail("更新数据已失效");
			}
		}

		// TODO 发送邮件和短信通知，这里采用异步发送
		// 订单支付成功以后，会发送短信给用户，以及发送邮件给管理员
		// notifyService.notifyMail("新订单通知", order.toString());
		// 这里微信的短信平台对参数长度有限制，所以将订单号只截取后6位
		// notifyService.notifySmsTemplateSync(order.getMobile(),
		// NotifyType.PAY_SUCCEED,
		// new String[] { orderSn.substring(8, 14) });

		// 请依据自己的模版消息配置更改参数
		// String[] parms = new String[] { order.getOrderSn(),
		// order.getOrderPrice().toString(),
		// DateTimeUtil.getDateTimeDisplayString(order.getAddTime()),
		// order.getConsignee(), order.getMobile(),
		// order.getAddress() };

		// notifyService.notifyWxTemplate(result.getOpenid(),
		// NotifyType.PAY_SUCCEED, parms,
		// "pages/index/index?orderId=" + order.getId());

		return WxPayNotifyResponse.success("处理成功!");
	}

	/**
	 * 订单申请退款
	 * <p>
	 * 1. 检测当前订单是否能够退款； 2. 设置订单申请退款状态。
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 订单退款操作结果
	 */
	public Object refund(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer orderId = JacksonUtil.parseInteger(body, "orderId");
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}

		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgument();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		OrderHandleOption handleOption = OrderUtil.build(order);
		if (!handleOption.isRefund()) {
			return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
		}

		// 设置订单申请退款状态
		order.setOrdOrderStatus(OrderUtil.STATUS_REFUND);
		if (orderService.updateWithOptimisticLocker(order) == 0) {
			return ResponseUtil.updatedDateExpired();
		}

		// TODO 发送邮件和短信通知，这里采用异步发送
		// 有用户申请退款，邮件通知运营人员
		// notifyService.notifyMail("退款申请", order.toString());

		return ResponseUtil.ok();
	}

	/**
	 * 确认收货
	 * <p>
	 * 1. 检测当前订单是否能够确认收货； 2. 设置订单确认收货状态。
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	public Object confirm(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer orderId = JacksonUtil.parseInteger(body, "orderId");
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}

		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgument();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		OrderHandleOption handleOption = OrderUtil.build(order);
		if (!handleOption.isConfirm()) {
			return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能确认收货");
		}

		// Short comments = orderGoodsService.getComments(orderId);
		// order.setComments(comments);

		order.setOrdOrderStatus(OrderUtil.STATUS_CONFIRM);
		order.setOrdConfirmTime(LocalDateTime.now());
		if (orderService.updateWithOptimisticLocker(order) == 0) {
			return ResponseUtil.updatedDateExpired();
		}
		return ResponseUtil.ok();
	}

	/**
	 * 删除订单
	 * <p>
	 * 1. 检测当前订单是否可以删除； 2. 删除订单。
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	public Object delete(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer orderId = JacksonUtil.parseInteger(body, "orderId");
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}

		TicOrder order = orderService.findById(orderId);
		if (order == null) {
			return ResponseUtil.badArgument();
		}
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		OrderHandleOption handleOption = OrderUtil.build(order);
		if (!handleOption.isDelete()) {
			return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能删除");
		}

		// 订单order_status没有字段用于标识删除
		// 而是存在专门的delete字段表示是否删除
		orderService.deleteById(orderId);

		return ResponseUtil.ok();
	}

	/**
	 * 待评价订单商品信息
	 *
	 * @param userId  用户ID
	 * @param orderId 订单ID
	 * @param goodsId 商品ID
	 * @return 待评价订单商品信息
	 */
	public Object goods(Integer userId, Integer orderId, Integer goodsId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		List<TicOrderGoods> orderGoodsList = orderGoodsService.findByOidAndGid(orderId, goodsId);
		int size = orderGoodsList.size();

		Assert.state(size < 2, "存在多个符合条件的订单商品");

		if (size == 0) {
			return ResponseUtil.badArgumentValue();
		}

		TicOrderGoods orderGoods = orderGoodsList.get(0);
		return ResponseUtil.ok(orderGoods);
	}

	/**
	 * 评价订单商品
	 * <p>
	 * 确认商品收货或者系统自动确认商品收货后7天内可以评价，过期不能评价。
	 *
	 * @param userId 用户ID
	 * @param body   订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	public Object comment(Integer userId, String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		Integer comOrderId = JacksonUtil.parseInteger(body, "comOrderId");
		if (comOrderId == null) {
			return ResponseUtil.badArgument();
		}
		/*
		 * TicOrderGoods orderGoods = orderGoodsService.findById(comOrderId); if
		 * (orderGoods == null) { return ResponseUtil.badArgumentValue(); }
		 * Integer orderId = orderGoods.getOrgOrderId();
		 */
		TicOrder order = null;
		order = orderService.findById(comOrderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		/*
		 * Short orderStatus = order.getOrdOrderStatus(); if
		 * (!OrderUtil.isConfirmStatus(order) &&
		 * !OrderUtil.isAutoConfirmStatus(order)) { return
		 * ResponseUtil.fail(ORDER_INVALID_OPERATION, "当前商品不能评价"); }
		 */
		if (!order.getOrdUserId().equals(userId)) {
			return ResponseUtil.fail(ORDER_INVALID, "当前商品不属于用户");
		}
		Integer commentId = order.getOrdComment();
		if (commentId == -1) {
			return ResponseUtil.fail(ORDER_COMMENT_EXPIRED, "当前商品评价时间已经过期");
		}
		if (commentId != 0) {
			return ResponseUtil.fail(ORDER_COMMENTED, "订单商品已评价");
		}

		String content = JacksonUtil.parseString(body, "comContent");
		Integer star = JacksonUtil.parseInteger(body, "comStar");
		if (star == null || star < 0 || star > 5) {
			return ResponseUtil.badArgumentValue();
		}
		Boolean hasPicture = JacksonUtil.parseBoolean(body, "comHasPicture");
		List<String> picUrls = JacksonUtil.parseStringList(body, "comPicUrls");
		if (hasPicture == null || !hasPicture) {
			picUrls = new ArrayList<>(0);
		}

		// 1. 创建评价
		TicComment comment = new TicComment();
		comment.setComUserId(userId);
		comment.setComOrderId(comOrderId);
		comment.setComContent(content);
		comment.setComHasPicture(hasPicture);
		comment.setComPicUrls(picUrls.toArray(new String[]{}));
		commentService.save(comment);

		// 2. 更新订单商品的评价列表
		order.setOrdComment(comment.getComId());
		orderService.update(order);

		// 3. 更新订单中未评价的订单商品可评价数量
		//// Short commentCount = order.getComments();
		// if (commentCount > 0) {
		// commentCount--;
		// }
		// order.setComments(commentCount);
		// orderService.updateWithOptimisticLocker(order);

		return ResponseUtil.ok();
	}

}