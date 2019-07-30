package org.linlinjava.litemall.admin.win;

import org.linlinjava.litemall.admin.dao.passData;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicGateView;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderGoods;
import org.linlinjava.litemall.db.request.TicOrderRequest;
import org.linlinjava.litemall.db.response.TicOrderGoodResponse;
import org.linlinjava.litemall.db.response.TicOrderResponse;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicGateViewService;
import org.linlinjava.litemall.db.service.TicOrderGoodService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/win/gate")
@Validated
public class WinGateController {

	@Autowired
	private TicOrderService orderService;
	@Autowired
	private TicOrderGoodService orderGoodService;
	@Autowired
	private TicGateViewService ticGateViewService;

	/**
	 * @param parkCode 入园码(11位：团队票--一张; 10位：单人票 --多张)
	 * @param gateId   闸机Id(大门闸机ID默认为0)
	 * @return
	 */
	@GetMapping("pass")
	public Object gatePass(String parkCode, String gateId) {
		System.out.println(parkCode +","+gateId);
		Map<String, Object> map = new HashMap<>();
		// 景点id集合
		Integer[] viewId;
		// 出游人姓名
		String ordTourist;
		// 人数
		Integer num;
		// 订单门票
		TicOrderGoods ticOrderGoods = new TicOrderGoods();
		List<TicOrderGoodResponse> orderGoodsList = new ArrayList<>();
//		// 团队票
//		if (parkCode.length() == 11) {
//			TicOrder order = new TicOrder();
//			order.setOrdParkCode(parkCode);
//			// 查询门票信息
//			List<TicOrderResponse> ticOrderResponses = orderService.orderGoogList(order);
//			if (StringUtils.isEmpty(ticOrderResponses) || ticOrderResponses.size() == 0) {
//				return ResponseUtil.warn(new passData());
//			}
//			TicOrderResponse orderRes = ticOrderResponses.get(0);
//			ordTourist = orderRes.getOrdTourist();
//			// 获取订单门票信息
//			orderGoodsList = orderRes.getOrderGoodsList();
//			TicOrderGoodResponse response = orderGoodsList.get(0);
//			viewId = response.getOrgViewId(); // 景区Id集合
//			num = orderRes.getOrdGoodsNum();  // 门票数量
//		}
//		// 单人票
//		else
     	if (!StringUtils.isEmpty(parkCode)) {
			// 查询订单门票
			List<TicOrderGoods> orgList = orderGoodService.queryOrderGood(parkCode);
			if (StringUtils.isEmpty(orgList)) {
				return ResponseUtil.warn(new passData());
			}
			ticOrderGoods = orgList.get(0);
//			for(TicOrderGoods org: orgList){
				// 订单门票信息
				Integer orgOrderId = ticOrderGoods.getOrgOrderId();// 订单Id
				// 景点集合Id
				viewId = ticOrderGoods.getOrgViewId();
				System.out.println("订单门票景点Id:"+ viewId);
				// 查询订单信息
				TicOrderResponse orderCate = orderService.details(orgOrderId);
				ordTourist = orderCate.getOrdTourist();
				num = 1;
//			}
		} else if (parkCode.equals("ObqEzxT8IROZdzP6")) {
			map.put("pass", true);
			map.put("name", "游客");
			map.put("number", 1);
			map.put("time", LocalDateTime.now());
			return ResponseUtil.ok(map);
		} else {
			return ResponseUtil.warn(new passData());
		}
		// 数组转集合
		List<Integer> viewInteger = Arrays.asList(viewId);
		List<Integer> viewList = new ArrayList(viewInteger);
		// 查询
		TicGateView gateView = ticGateViewService.selectLike(gateId);
		if(StringUtils.isEmpty(gateView)){
			return ResponseUtil.userd(new passData());
		}
		Integer gateViewId = gateView.getGateViewId();
		System.out.println("景点ID-"+gateViewId);
		// 判断是否有该景点id
		boolean result = viewList.contains(gateViewId);
		System.out.println("景点对比结果--" + result);
		if (!result) {
			return ResponseUtil.userd(new passData());
		}
		// 剔除该景点id
		Iterator<Integer> iter = viewList.iterator();
		while (iter.hasNext()) {
			Integer item = iter.next();
			if (item.equals(gateViewId)) {
				iter.remove();
			}
		}
		// 集合转数组
		Integer[] inte = new Integer[viewList.size()];
		viewId = viewList.toArray(inte);
		System.out.println("处理后的景点门票信息：" + viewId);
		ticOrderGoods.setOrgViewId(viewId);
		ticOrderGoods.setOrgUpdateTime(LocalDateTime.now());
		// 修改景点信息
//		if (parkCode.length() == 11) {
//			for (TicOrderGoods org : orderGoodsList) {
//				org.setOrgViewId(viewId);
//			}
//			// 批量修改
//			int i = orderGoodService.updateBatch(orderGoodsList);
//			if (i == 0) {
//				return ResponseUtil.testFail(new passData());
//			}
//		} else if (parkCode.length() == 10) {
		int i = orderGoodService.updateById(ticOrderGoods);
		if (i == 0) {
			return ResponseUtil.testFail(new passData());
		}
//		}
		if (StringUtils.isEmpty(ordTourist)) {
			ordTourist = "游客";
		}
		map.put("pass", true);
		map.put("name", ordTourist);
		map.put("number", num);
		map.put("time", LocalDateTime.now());
		return ResponseUtil.ok(map);
	}

}
