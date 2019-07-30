package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallOrderGoodsMapper;
import org.linlinjava.litemall.db.dao.TicOrderGoodsMapper;
import org.linlinjava.litemall.db.domain.LitemallOrderGoods;
import org.linlinjava.litemall.db.domain.LitemallOrderGoodsExample;
import org.linlinjava.litemall.db.domain.TicOrderGoods;
import org.linlinjava.litemall.db.domain.TicOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallOrderGoodsService {
    @Resource
    private TicOrderGoodsMapper orderGoodsMapper;

    public int add(TicOrderGoods orderGoods) {
        orderGoods.setOrgAddTime(LocalDateTime.now());
        orderGoods.setOrgUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertSelective(orderGoods);
    }

    public List<TicOrderGoods> queryByOid(Integer orderId) {
    	TicOrderGoodsExample example = new TicOrderGoodsExample();
        example.or().andOrgOrderIdEqualTo(orderId).andOrgDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public List<TicOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
    	TicOrderGoodsExample example = new TicOrderGoodsExample();
        example.or().andOrgOrderIdEqualTo(orderId).andOrgGoodsIdEqualTo(goodsId).andOrgDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public TicOrderGoods findById(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    public void updateById(TicOrderGoods orderGoods) {
        orderGoods.setOrgUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    public Short getComments(Integer orderId) {
        TicOrderGoodsExample example = new TicOrderGoodsExample();
        example.or().andOrgOrderIdEqualTo(orderId).andOrgDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    public boolean checkExist(Integer goodsId) {
    	TicOrderGoodsExample example = new TicOrderGoodsExample();
        example.or().andOrgGoodsIdEqualTo(goodsId).andOrgDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }
}
