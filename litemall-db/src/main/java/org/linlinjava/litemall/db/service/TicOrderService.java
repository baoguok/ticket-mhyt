package org.linlinjava.litemall.db.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.OrderMapper;
import org.linlinjava.litemall.db.dao.TicOrderMapper;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderExample;
import org.linlinjava.litemall.db.request.TicOrderRequest;
import org.linlinjava.litemall.db.response.TicOrderNumResponse;
import org.linlinjava.litemall.db.response.TicOrderResponse;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 订单信息表
 * @author liuyu
 */
@Service
public class TicOrderService {
    @Resource
    private TicOrderMapper ticOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    
    /**
     * 条件查询订单信息
     * @param orderReq
     * @return
     * @author liuyu
     */
    public List<TicOrder> list(TicOrderRequest orderReq){
        TicOrderExample example = new TicOrderExample();
        TicOrderExample.Criteria criteria = example.createCriteria();
        // 用户Id
        if(!StringUtils.isEmpty(orderReq.getOrdUserId())) {
            criteria.andOrdUserIdEqualTo(orderReq.getOrdUserId());
        }
        // 订单编号
        if(!StringUtils.isEmpty(orderReq.getOrdOrderSn())) {
            criteria.andOrdOrderSnLike(orderReq.getOrdOrderSn());
        }
        // 订单状态
        if(!StringUtils.isEmpty(orderReq.getOrdOrderStatus())) {
            criteria.andOrdOrderStatusEqualTo(orderReq.getOrdOrderStatus());
        }
        // 出游人姓名
        if(!StringUtils.isEmpty(orderReq.getOrdTourist())) {
            criteria.andOrdTouristLike(orderReq.getOrdTourist());
        }
        // 出游人手机
        if(!StringUtils.isEmpty(orderReq.getOrdMobile())) {
            criteria.andOrdMobileLike(orderReq.getOrdMobile());
        }
        // 用户身份证
        if(!StringUtils.isEmpty(orderReq.getOrdUcardId())) {
            criteria.andOrdUcardIdLike(orderReq.getOrdUcardId());
        }
        // 入园码
        if(!StringUtils.isEmpty(orderReq.getOrdColCode())){
            criteria.andOrdColCodeEqualTo(orderReq.getOrdColCode());
        }
        // 售卖方式
        if(!StringUtils.isEmpty(orderReq.getOrdPayWay())) {
            criteria.andOrdPayWayEqualTo(orderReq.getOrdPayWay());
        }
        criteria.andOrdDeletedEqualTo(false);
        Integer page = orderReq.getPage();
        Integer limit = orderReq.getLimit();
        PageHelper.startPage(page, limit);
        return ticOrderMapper.selectByExample(example);
    }

    /**
     * 条件查询订单信息(订单状态)
     * @param orderReq
     * @return
     * @author liuyu
     */
	public List<TicOrderResponse> listOrder(TicOrderRequest orderReq,String type,Integer page,Integer limit){
         List<Short> list = new ArrayList<Short>();
         if(type.equals("cancel")){ // 	已取消的
        	 list.add((short) 102);
        	 list.add((short) 103);
        	 list.add((short) 203);
         } else if (type.equals("tobepaid")) {	// 	待支付
        	 list.add((short) 101);
         } else if (type.equals("tobeused")) {	//	待使用
        	 list.add((short) 201);
         } else if (type.equals("alreadyused")) {	//	已使用
        	 list.add((short) 301);
         }
        orderReq.setLsit(list);
        if(!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)){
            orderReq.setPage((page - 1) * limit);
            orderReq.setLimit(limit);
        }
        return ticOrderMapper.listOrder(orderReq);
    }

    /**
     * 查询订单以及订单门票信息
     * @param order （手机号、取票码、身份证）
     * @return
     * @author liuyu
     */
    public List<TicOrderResponse> orderGoogList(TicOrder order){
	    return ticOrderMapper.orderGoodList(order);
    }
	/**
	 * 修改已过期订单
	 */
	public void BeOverdue(){
		ticOrderMapper.BeOverdue();
	}
	
	/**
	 * 订单详情
	 * @param ordId
	 * @return
	 */
	public TicOrderResponse details(Integer ordId){
		return ticOrderMapper.details(ordId);
	}


    /**
     * 用户订单详情
     * @param map
     * @return
     */
    public List<TicOrderResponse> UserDetails(@SuppressWarnings("rawtypes") Map map){
    	return ticOrderMapper.UserDetails(map);
    }
    /**
     * 创建订单信息
     * @param ticOrder
     * @return
     * @author liuyu
     */
    public int create(TicOrder ticOrder){
        ticOrder.setOrdAddTime(LocalDateTime.now());
        ticOrder.setOrdUpdateTime(LocalDateTime.now());
        return ticOrderMapper.insertSelective(ticOrder);
    }

    /**
     * 修改订单信息
     * @param ticOrder
     * @return
     * @author liuyu
     */
    public int update(TicOrder ticOrder){
        if(StringUtils.isEmpty(ticOrder.getOrdId())){
            return 0;
        }
        ticOrder.setOrdUpdateTime(LocalDateTime.now());
        return ticOrderMapper.updateByPrimaryKeySelective(ticOrder);
    }

    /**
     * 修改订单状态(by orderSn)
     * @param orderSn
     * @return
     * @author liuyu
     */
    public int updateOrderSn(String orderSn){
        if(StringUtils.isEmpty(orderSn)){
            return 0;
        }
        return ticOrderMapper.updateOrderStatus(orderSn);
    }
    /**
     * 逻辑删除订单信息
     * @param ticOrderId
     * @return
     * @author liuyu
     */
    public int delete(Integer ticOrderId){
        if(StringUtils.isEmpty(ticOrderId)){
            return 0;
        }
        TicOrder ticOrder = new TicOrder();
        ticOrder.setOrdId(ticOrderId);
        ticOrder.setOrdUpdateTime(LocalDateTime.now());
        ticOrder.setOrdDeleted(true);
        return ticOrderMapper.updateByPrimaryKeySelective(ticOrder);
    }

    public TicOrder findById(Integer orderId) {
        return ticOrderMapper.selectByPrimaryKey(orderId);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int countByOrderSn(Integer userId, String orderSn) {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdUserIdEqualTo(userId).andOrdOrderSnEqualTo(orderSn).andOrdDeletedEqualTo(false);
        return (int) ticOrderMapper.countByExample(example);
    }

    // TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = getRandomNum(6);
        }
        return orderSn;
    }

    public List<TicOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus) {
        TicOrderExample example = new TicOrderExample();
//        example.setOrderByClause(TicOrder.Column.addTime.desc());
        TicOrderExample.Criteria criteria = example.or();
        criteria.andOrdUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrdOrderStatusIn(orderStatus);
        }
        criteria.andOrdDeletedEqualTo(false);
        return ticOrderMapper.selectByExample(example);
    }

    public int countByOrderStatus(Integer userId, List<Short> orderStatus) {
        TicOrderExample example = new TicOrderExample();
        TicOrderExample.Criteria criteria = example.or();
        criteria.andOrdUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrdOrderStatusIn(orderStatus);
        }
        criteria.andOrdDeletedEqualTo(false);
        return (int) ticOrderMapper.countByExample(example);
    }

    public List<TicOrder> querySelective(Integer userId, String orderSn, List<Short> orderStatusArray, Integer page, Integer size, String sort, String order) {
        TicOrderExample example = new TicOrderExample();
        TicOrderExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andOrdUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrdOrderSnEqualTo(orderSn);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            criteria.andOrdOrderStatusIn(orderStatusArray);
        }
        criteria.andOrdDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return ticOrderMapper.selectByExample(example);
    }

    public int updateWithOptimisticLocker(TicOrder order) {
        LocalDateTime preUpdateTime = order.getOrdUpdateTime();
        order.setOrdUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    public void deleteById(Integer id) {
        ticOrderMapper.logicalDeleteByPrimaryKey(id);
    }
    public int count() {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdDeletedEqualTo(false);
        return (int) ticOrderMapper.countByExample(example);
    }

    public int countRefundApply() {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdOrderStatusEqualTo((short)202);
        return (int) ticOrderMapper.countByExample(example);
    }

    public int countShipOrder() {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdOrderStatusEqualTo((short)201);
        return (int) ticOrderMapper.countByExample(example);
    }

    public List<TicOrder> queryUnpaid() {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andOrdDeletedEqualTo(false);
        return ticOrderMapper.selectByExample(example);
    }

    public TicOrder findBySn(String orderSn) {
        TicOrderExample example = new TicOrderExample();
        example.or().andOrdOrderSnEqualTo(orderSn).andOrdDeletedEqualTo(false);
        return ticOrderMapper.selectOneByExample(example);
    }

    /*当日售票信息*/
    public List<TicOrderNumResponse> ticNumber(Integer page, Integer size, Integer ordPayWay, Integer ordAdminId){
        TicOrderExample example = new TicOrderExample();
        TicOrderExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(ordPayWay)) {
            criteria.andOrdPayWayEqualTo(ordPayWay);
        }
        if (!StringUtils.isEmpty(ordAdminId)) {
            criteria.andOrdAdminIdEqualTo(ordAdminId);
        }
        criteria.andOrdDeletedEqualTo(false);
        PageHelper.startPage(page, size);
        Map map = new HashMap();
        map.put("ordAdminId",ordAdminId);
        map.put("ordPayWay",ordPayWay);
        return ticOrderMapper.ticNumber(map);
    }

    /*查询全部售票信息*/
    public List<TicOrderNumResponse> ticNumberAll(Integer ordAdminId,Integer ordOrderStatus,Integer ordPayWay,Integer day){
        Map map = new HashMap();
        map.put("ordAdminId",ordAdminId);
        map.put("ordOrderStatus",ordOrderStatus);
        map.put("ordPayWay",ordPayWay);
        map.put("day",day);
        return ticOrderMapper.ticNumberAll(map);
    }
}
