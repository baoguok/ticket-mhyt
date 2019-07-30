package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicOrderGoodsMapper;
import org.linlinjava.litemall.db.domain.OrderGoodsReturn;
import org.linlinjava.litemall.db.domain.TicOrderGoods;
import org.linlinjava.litemall.db.domain.TicOrderGoodsExample;
import org.linlinjava.litemall.db.response.TicOrderGoodResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单门票信息表
 * @author liuyu
 */
@Service
public class TicOrderGoodService {
    @Resource
    private TicOrderGoodsMapper orderGoodsMapper;

    /**
     * 创建订单门票信息（批量）
     * @param list
     * @return
     * @author liuyu
     */
    public int createBatch(List<TicOrderGoods> list){
        return orderGoodsMapper.addBatch(list);
    }

    /**
     * 根据订单ID查询购买的门票及数量
     * @param ordId
     * @return
     */
    public List<OrderGoodsReturn> getOrderGoodsByOrdId(Integer ordId) {
        return orderGoodsMapper.getOrderGoodsByOrdId(ordId);
    }

    /**
     * 查询门票信息 by
     * @param colCode 入园码
     * @return
     */
    public List<TicOrderGoods> queryOrderGood(String colCode){
//        return orderGoodsMapper.queryByColCode(colCode);
        TicOrderGoodsExample example = new TicOrderGoodsExample();
        example.or().andOrgColCodeEqualTo(colCode).andOrgDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    /**
     * 修改订单门票信息
     * @param org
     * @return
     */
    public int updateById(TicOrderGoods org){
        Integer orgId = org.getOrgId();
        if(StringUtils.isEmpty(orgId)){
            return 0;
        }
        return orderGoodsMapper.updateByPrimaryKeySelective(org);
    }

    /**
     * 批量修改景点id
     * @param org
     * @return
     */
    public int updateBatch(List<TicOrderGoodResponse> list){
        return orderGoodsMapper.updateBatch(list);
    }
}
