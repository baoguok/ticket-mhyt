package org.linlinjava.litemall.admin.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.WriterException;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.QrCodeCreateUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderGoods;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicGateViewService;
import org.linlinjava.litemall.db.service.TicOrderGoodService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class WinOrderService {
	@Autowired
	private TicOrderService orderService;
	@Autowired
	private QrCodeCreateUtil qrCodeUtil;
	@Autowired
	private TicOrderGoodService orgService;
	@Autowired
	private TicCategoryService categoryService;
	@Autowired
	private TicGateViewService gateViewService;


	/**
	 * 创建订单
	 *
	 * @param body 订单信息, { ordCatId: xxx, adminId: xxx, TotalPrice:xxx ,couDiscount: xxx,params: [TicGoods]}
	 * @return
	 */
	@Transactional
	public Map<String, Integer> create(String body,Integer catPeopleNum) {
		Map<String, Integer> map = new HashMap<>();
		if (body == null) {
			map.put("errno", -1);
			return map;
		}
		TicOrder order = new TicOrder();
		// 获取所购买的所有门票及数量
		Integer start = body.indexOf("[");
		Integer end = body.lastIndexOf("]");
		String StrGoods = body.substring(start, end + 1);
		Gson gson = new Gson();
		//获取门票信息集合
		List<TicGoods> list = gson.fromJson(StrGoods, new TypeToken<List<TicGoods>>() {
		}.getType());// 获取所购买的所有门票及数量
		// 创建订单信息入参
		order.setOrdUserId(-1); // 用户ID
		order.setOrdOrderSn(getUUID()); // 订单编号
		order.setOrdOrderStatus(Short.parseShort("101")); // 订单状态
		order.setOrdPayId(getUUID()); // 微信支付编号
		order.setOrdPayTime(LocalDateTime.now()); // 微信支付时间
		order.setOrdPayWay(JacksonUtil.parseInteger(body, "ordPayWay")); // 购买方式()
		Integer adminId = Integer.valueOf(JacksonUtil.parseString(body, "adminId"));
		order.setOrdCouponId(-1);//优惠券Id
		BigDecimal couDiscount = new BigDecimal(JacksonUtil.parseString(body, "couDiscount"));
		order.setOrdCouponPrice(couDiscount); // 优惠券金额
		BigDecimal TotalPrice = new BigDecimal(JacksonUtil.parseString(body, "TotalPrice"));
		order.setOrdGoodsPrice(TotalPrice); // 订单总价
		order.setOrdOrderPrice(TotalPrice); //订单价格
		order.setOrdActualPrice(TotalPrice.subtract(couDiscount)); // 实付金额
		order.setOrdAdminId(adminId); // 售票员ID
		order.setOrdConfirmTime(LocalDateTime.now()); // 使用时间
		order.setOrdColCode(getUUID()); // 取票码
		order.setOrdParkCode(getUUID());// 入园码
		order.setOrdGoodsNum(list.size());// 门票数量
		Integer ordCatId = Integer.valueOf(JacksonUtil.parseString(body, "ordCatId"));
		order.setOrdCatId(ordCatId);// 门票类别Id
		order.setOrdComment(0);// 是否可评价
		order.setOrdPayTime(LocalDateTime.now());// 支付时间
		order.setOrdDeleted(false);
		// 查询景点Id集合
		TicCategory query = categoryService.query(ordCatId);
		Integer[] catViewId = query.getCatViewId();
		// 景点ID集合
		List<Integer> viewInteger = Arrays.asList(catViewId);
		for (Integer viewId : viewInteger) {

		}
		// 添加大门闸机ID
		List<Integer> viewList = new ArrayList(viewInteger);
		viewList.add(0);
		Integer[] inte = new Integer[viewList.size()];
		catViewId = viewList.toArray(inte);
		order.setOrdQrCode("窗口售票二维码");
		// 生成二维码地址
        /*try {
            String url =qrCodeUtil.createQrCode(
				       new FileOutputStream(new File("storage\\" + order.getOrdColCode() + ".jpg")),
				            order.getOrdColCode(), 300, "JPEG");
            order.setOrdQrCode(url);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
		//添加订单信息
		int orderAdd = orderService.create(order);
		if (orderAdd == 0) {
			map.put("errno", -1);
			return map;
		}
		List<TicOrderGoods> orgList = new ArrayList<>();
		int size = list.size();
		if(size == 0){
			map.put("errno", -1);
			return map;
		}
		if (catPeopleNum > 1) {
			TicGoods goods = list.get(0);
			for (int i = 0; i < catPeopleNum*size; i++) {
				TicOrderGoods orderGoods = new TicOrderGoods();
				orderGoods.setOrgOrderId(order.getOrdId());
				orderGoods.setOrgGoodsId(goods.getGooId());
				orderGoods.setOrgGoodsName(goods.getGooName());
				orderGoods.setOrgNumber((short) 1);
				orderGoods.setOrgViewId(catViewId);
				orderGoods.setOrgAddTime(LocalDateTime.now());
				orderGoods.setOrgUpdateTime(LocalDateTime.now());
				orderGoods.setOrgPrice(goods.getGooRetailPrice());// 门票价格
				orderGoods.setOrgColCode(getUUID());// 入园码
				orderGoods.setOrgDeleted(false);
				orgList.add(orderGoods);
			}
		} else {
			//创建订单门票关联信息入参
			for (TicGoods goods : list) {
				TicOrderGoods orderGoods = new TicOrderGoods();
				orderGoods.setOrgOrderId(order.getOrdId());
				orderGoods.setOrgGoodsId(goods.getGooId());
				orderGoods.setOrgGoodsName(goods.getGooName());
				orderGoods.setOrgNumber((short) 1);
				orderGoods.setOrgViewId(catViewId);
				orderGoods.setOrgAddTime(LocalDateTime.now());
				orderGoods.setOrgUpdateTime(LocalDateTime.now());
				orderGoods.setOrgPrice(goods.getGooRetailPrice());// 门票价格
				orderGoods.setOrgColCode(getUUID());// 入园码
				orderGoods.setOrgDeleted(false);
				orgList.add(orderGoods);
			}
		}
		//添加订单门票信息
		int batch = orgService.createBatch(orgList);
		if (batch == 0) {
			map.put("errno", -1);
			return map;
		}
		if(catPeopleNum > 1 || list.size() == 1){
			map.put("num", catPeopleNum);
		} else {
			map.put("num", list.size());
		}
		map.put("orderId", order.getOrdId());
		map.put("errno", 0);
		return map;
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

	// 生成15位唯一性的入园码
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
		return str + s + c;
	}

	/**
	 * 生成16位
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
		return str + s + c;
	}

}
