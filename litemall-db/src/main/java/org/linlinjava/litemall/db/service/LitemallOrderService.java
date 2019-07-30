package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;

import org.linlinjava.litemall.db.dao.OrderMapper;
import org.linlinjava.litemall.db.dao.TicOrderMapper;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderExample;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class LitemallOrderService {
    @Resource
    private TicOrderMapper ticOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    public int add(TicOrder order) {
        order.setOrdAddTime(LocalDateTime.now());
        order.setOrdUpdateTime(LocalDateTime.now());
        return ticOrderMapper.insertSelective(order);
    }

    public int count(Integer userId) {
    	TicOrderExample example = new TicOrderExample();
        example.or().andOrdUserIdEqualTo(userId).andOrdDeletedEqualTo(false);
        return (int) ticOrderMapper.countByExample(example);
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
    
//    public List<TicOrder> queryUnpaid() {
//    	TicOrderExample example = new TicOrderExample();
//        example.or().andOrdOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andOrdDeletedEqualTo(false);
//        return ticOrderMapper.selectByExample(example);
//    }

    public TicOrder findBySn(String orderSn) {
    	TicOrderExample example = new TicOrderExample();
        example.or().andOrdOrderSnEqualTo(orderSn).andOrdDeletedEqualTo(false);
        return ticOrderMapper.selectOneByExample(example);
    }
    /*public Map<Object, Object> orderInfo(Integer userId) {
    	TicOrderExample example = new TicOrderExample();
        example.or().andOrdUserIdEqualTo(userId).andOrdDeletedEqualTo(false);
       List<TicOrder> orders = ticOrderMapper.selectByExampleSelective(example, TicOrder.Column.orderStatus, TicOrder.Column.comments);

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (TicOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
           } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getOrdComments();
            } else {
                // do nothing
            }
        }

       Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
       return orderInfo;

    }*/

    /*public List<TicOrder> queryComment() {
    	TicOrderExample example = new TicOrderExample();
        example.or().andOrdCommentsGreaterThan((short) 0).andDeletedEqualTo(false);
       return ticOrderMapper.selectByExample(example);
    }*/
}
