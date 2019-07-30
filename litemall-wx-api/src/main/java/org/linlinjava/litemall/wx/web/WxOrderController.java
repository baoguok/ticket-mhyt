package org.linlinjava.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.NotifyType;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.OrderGoodsReturn;
import org.linlinjava.litemall.db.domain.TicCouponUser;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.request.TicOrderRequest;
import org.linlinjava.litemall.db.response.TicOrderResponse;
import org.linlinjava.litemall.db.service.TicCouponUserService;
import org.linlinjava.litemall.db.service.TicOrderGoodService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/order")
@Validated
public class WxOrderController {
    private final Log logger = LogFactory.getLog(WxOrderController.class);
    
    @Autowired
    private WxOrderService wxOrderService;

    @Autowired
    private TicOrderService orderService;
    @Autowired
    private TicCouponUserService couponUserService;
    @Autowired
    private TicOrderGoodService orderGoodService;
    
	@Autowired
	private NotifyService notifyService;

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息
     * @param page     分页页数
     * @param size     分页大小
     * @return 订单列表
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId,
                       @RequestParam(defaultValue = "0") Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return wxOrderService.list(userId, showType, page, size);
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer orderId) {
        return wxOrderService.detail(userId, orderId);
    }

    /**
     * 小程序提交订单
     *
     * @param userId 用户ID
     * @param body
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public Object submit(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.submit(userId, body);
    }
    /**
     * 自助取票机提交订单
     *
     * @param body
     * @return 提交订单操作结果
     */
    @RequestMapping(value = "shsub",method = RequestMethod.POST)
    public Object SelfHelpsubmit(@RequestBody String body){
    	System.out.println(body); 
		return wxOrderService.SelfHelpsubmit(body);
    }

    /**
     * 取消订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public Object cancel(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.cancel(userId, body);
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    public Object prepay(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
        return wxOrderService.prepay(userId, body, request);
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     *  TODO
     *  注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request 请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @PostMapping("pay-notify")
    public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("---访问daol");
        return wxOrderService.payNotify(request, response);
    }

    /**
     * 订单申请退款
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("refund")
    public Object refund(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.refund(userId, body);
    }

    /**
     * 确认收货
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("confirm")
    public Object confirm(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.confirm(userId, body);
    }

    /**
     * 删除订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.delete(userId, body);
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @GetMapping("goods")
    public Object goods(@LoginUser Integer userId,
                        @NotNull Integer orderId,
                        @NotNull Integer goodsId) {
        return wxOrderService.goods(userId, orderId, goodsId);
    }

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("comment")
    public Object comment(@LoginUser Integer userId, @RequestBody String body) {
        return wxOrderService.comment(userId, body);
    }

    /**
     * 修改订单和优惠券状态
     * @param userId
     * @param orderId
     * @param
     * @return
     * @author liuyu
     */
    @SuppressWarnings("static-access")
	@GetMapping("updateOrd")
    public Object updateOrd(@LoginUser Integer userId, Integer orderId, Integer cosId,boolean status){
        System.out.println(cosId+" , "+status);
        Short orderStatus;
        Short cosStatus;
        TicOrder OrderSMS = orderService.details(orderId);
        NotifyType notifyType = null;
        // true支付,false退款
        if(status){
            orderStatus = 201;
            cosStatus = 1;
        	// 腾讯短信平台
            String time = OrderSMS.getOrdConfirmTime()+"";
        	String[] Tenparams = new String[] {  OrderSMS.getOrdTourist(),time.split(" ")[0]+"的门票",OrderSMS.getOrdColCode(),OrderSMS.getOrdQrCode()  };
        	notifyService.notifySmsTemplate(OrderSMS.getOrdMobile(), notifyType.PAY_SUCCEED, Tenparams);
        }else{
            orderStatus = 103;
            cosStatus = 0;
            // 腾讯短信平台
            String time = OrderSMS.getOrdConfirmTime() + "";
        	String[] Tenparams = new String[] { OrderSMS.getOrdTourist(),time.split(" ")[0] };
        	notifyService.notifySmsTemplate(OrderSMS.getOrdMobile(), notifyType.REFUND, Tenparams);
        }
        if(StringUtils.isEmpty(orderId)){
            return ResponseUtil.badArgument();
        }
        // 修改订单状态
        TicOrder ticOrder = new TicOrder();
        ticOrder.setOrdId(orderId);
        ticOrder.setOrdOrderStatus(orderStatus);
        int update = orderService.update(ticOrder);
        System.out.println("修改订单："+update);
        if(update == 0){
            return  ResponseUtil.badArgument();
        }
        //修改优惠券状态
        if(cosId != -1){
            TicCouponUser couponUser = new TicCouponUser();
            couponUser.setCosId(cosId);
            couponUser.setCosOrderId(orderId);
            couponUser.setCosUserId(userId);
            couponUser.setCosUsedTime(LocalDateTime.now());
            couponUser.setCosStatus(cosStatus);
            int i = couponUserService.updateCou(couponUser);
            System.out.println("优惠券："+i);
            if(i == 0){
                return ResponseUtil.badArgument();
            }
        }
        return ResponseUtil.ok();
    }

    /**
     * 查询订单列表
     * @param userId
     * @return
     * @author liuyu
     */
    @GetMapping("listOrder")
    public Object listOrder(@LoginUser Integer userId,String type,Integer page,Integer limit) {
        if (userId == 0) {
            return ResponseUtil.unlogin();
        }
        orderService.BeOverdue(); // 修改已过期订单
        TicOrderRequest ordReq = new TicOrderRequest();
        ordReq.setOrdUserId(userId);
        List<TicOrderResponse> list = orderService.listOrder(ordReq,type,page,limit);
        long total = PageInfo.of(list).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("item",list);
        map.put("total",total);
        return ResponseUtil.ok(map);
    }

    /**
     * 查看订单详情
     * @param ordId
     * @return
     */
    @GetMapping("details")
    public Object details(Integer ordId) {
        TicOrderResponse order = orderService.details(ordId);
        List<OrderGoodsReturn>  orderGoods = orderGoodService.getOrderGoodsByOrdId(ordId);
        Map<String, Object> map = new HashMap<>();
        map.put("order",order);
        map.put("ordergoods",orderGoods);
        return ResponseUtil.ok(map);
    }

    @GetMapping("add")
    public Object add(TicOrder order) {
        int i = orderService.create(order);
        if(i == 0){
            return  ResponseUtil.badArgument();
        }
        return ResponseUtil.ok();
    }
}