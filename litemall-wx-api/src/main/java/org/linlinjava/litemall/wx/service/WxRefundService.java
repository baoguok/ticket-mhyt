package org.linlinjava.litemall.wx.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WxRefundService {
  @Autowired
  private TicCategoryService cateService;
  @Autowired
  private TicOrderService orderService;

  /**
   * 计算退票费用
   * @param catId
   * @param orderId
   * @author liuyu
   */
  public Map<String, Object> costRefund(Integer catId, Integer orderId) {
    Map<String,Object> map =  new HashMap<>();
    // 1.查询订单信息
    TicOrder order = orderService.findById(orderId);
    if (StringUtils.isEmpty(order)) {
      return null;
    }
    // 2.查询类目信息
    TicCategory cate = cateService.query(catId);
    if (StringUtils.isEmpty(cate)) {
      return null;
    }
    // 3.获取退票规则
    Integer retreDay = cate.getCatRetreDay();//退票天数(入园前多少天可退)
    BigDecimal retreCost = cate.getCatRetreCost();//退票手续费
    // 4.获取订单信息
    BigDecimal payCost = order.getOrdActualPrice();//支付金额
    Integer goodsNum = order.getOrdGoodsNum();// 门票数量
    Long conTime = order.getOrdConfirmTime().toEpochSecond(ZoneOffset.of("+8"));//门票使用日期
    Long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    Long spaceTime = conTime - nowTime;// 退票时距入园的时间(秒)
    // 换算(秒 --> 时)
    if ((spaceTime / 60 / 60) <= (retreDay * 24)){
      map.put("errno", -1);
      return map;
    }
    // 计算
    if( !retreCost.equals(0)){
      retreCost = retreCost.multiply(new BigDecimal(goodsNum));
      payCost = payCost.subtract(retreCost);
    }
    order.setOrdActualPrice(payCost);
    map.put("items", order);
    map.put("errno", 0);
    return map;
  }
}